package com.hwt.notes.basic;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TreeFuture {
    public static void main(String[] args) {
        //创建ThreadFactory
        ThreadFactory threadFactory = new ThreadFactory() {
            AtomicInteger count = new AtomicInteger();
            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "tf_" + count.getAndIncrement());
            }
        };
        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                10,
                5000,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000),
                threadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        // 创建根任务
        TaskNode<FutureTask<?>> taskTree = creatTestTaskTree();
        //拆分根任务
        InnerTask innerTask = new InnerTask(taskTree, threadPoolExecutor);
        threadPoolExecutor.submit(innerTask);
    }

    /**
     * 提交任务
     */
    static class InnerTask implements Runnable{
        TaskNode<FutureTask<?>> taskNode;
        ThreadPoolExecutor threadPoolExecutor;

        InnerTask(TaskNode<FutureTask<?>> taskNode,ThreadPoolExecutor threadPoolExecutor){
            this.taskNode = taskNode;
            this.threadPoolExecutor = threadPoolExecutor;
        }

        @Override
        public void run() {
            List<TaskNode<FutureTask<?>>> childTasks = taskNode.getChildTasks();
            if(CollectionUtil.isNotEmpty(childTasks)){
                int totalTaskNum = childTasks.size();
                CompleteFutureListener<FutureTask<?>> listener = new CompleteFutureListener<FutureTask<?>>() {
                    @Override
                    public void operationComplete(FutureTask<?> future) {
                        if (count.incrementAndGet() == totalTaskNum) {
                            taskNode.getFutureTask().run();
                        }
                    }
                };
                for (TaskNode<FutureTask<?>> childTask : childTasks) {
                    FutureTask<?> fTask = childTask.getFutureTask();
                    fTask = new FutureTaskAdapter(() -> null, fTask,listener);
                    childTask.setFutureTask(fTask);
                    threadPoolExecutor.submit(new InnerTask(childTask,threadPoolExecutor));
                }
                return;
            }
            threadPoolExecutor.submit(taskNode.getFutureTask());
        }
    }

    /**
     * 任务适配（最终任务的嵌套是一个链）
     */
    static class FutureTaskAdapter extends FutureTask{
        CompleteFutureListener<FutureTask<?>> listener;
        FutureTask<?> futureTask;

        public FutureTaskAdapter(Callable callable,FutureTask<?> futureTask,CompleteFutureListener<FutureTask<?>> listener) {
            //TODO  是否和一
            super(callable);
            this.futureTask = futureTask;
            this.listener = listener;
        }

        @Override
        public void run() {
            //TODO 是否转换
            futureTask.run();
            listener.operationComplete(this);
        }
    }

    /**
     * 完成监听者
     * @param <FutureTask>
     */
    static abstract class CompleteFutureListener<FutureTask extends Future<?>> implements FutureListener<FutureTask>{
        protected AtomicInteger count = new AtomicInteger();
    }


    /**
     * 任务树节点结构
     * @param <F>
     */
    @Data
    static class TaskNode<F extends FutureTask<?>>{
        private List<TaskNode<F>> childTasks;
        private FutureTask<?> futureTask;
        private String name;

        public TaskNode(FutureTask<?> futureTask){
            this.futureTask = futureTask;
        }
    }

    /**
     * @return 测试任务树
     */
    public static TaskNode<FutureTask<?>> creatTestTaskTree(){
        TaskNode<FutureTask<?>> root = createTask("root");
        List<TaskNode<FutureTask<?>>> childrenTask = createChildrenTask(root, 3);
        root.setChildTasks(childrenTask);
        TaskNode<FutureTask<?>> firstChild = getChildAndSetGrandson(root, 0, 3);
        TaskNode<FutureTask<?>> secondChild = getChildAndSetGrandson(root, 1, 4);
        TaskNode<FutureTask<?>> firstGrandson = getChildAndSetGrandson(firstChild, 2, 3);
        return root;
    }

    /**
     * 为子任务创建孙子任务
     * @param parent 父任务
     * @param childNum 子任务下标
     * @param grandsonNum 孙子任务数量
     * @return 子任务
     */
    public static TaskNode<FutureTask<?>> getChildAndSetGrandson(TaskNode<FutureTask<?>> parent,int childNum,int grandsonNum){
        TaskNode<FutureTask<?>> child = parent.getChildTasks().get(childNum);
        List<TaskNode<FutureTask<?>>> grandsonTasks = createChildrenTask(child, grandsonNum);
        child.setChildTasks(grandsonTasks);
        return child;
    }

    /**
     *
     * @param parent 父任务
     * @param num 子任务数量
     * @return 子任务列表
     */
    public static List<TaskNode<FutureTask<?>>> createChildrenTask(TaskNode<FutureTask<?>> parent,int num){
        ArrayList<TaskNode<FutureTask<?>>> taskNodes = new ArrayList<>();
        String name = parent.getName();
        for (int i = 0; i < num; i++) {
            String childName = name+"_" + i;
            TaskNode<FutureTask<?>> taskNode = createTask(childName);
            taskNodes.add(taskNode);
        }
        return taskNodes;
    }

    /**
     * 创建任务
     * @param name 任务名称
     * @return 创建出的任务
     */
    public static TaskNode<FutureTask<?>> createTask(String name){
        TaskNode<FutureTask<?>> taskNode = new TaskNode<>(new FutureTask<Void>(()->{
            System.out.println(Thread.currentThread().getName() + " : " +name);
            return null;
        }));
        taskNode.setName(name);
        return taskNode;
    }
}

package com.hwt.notes.datastructure.tree.express;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Stack;

public class ExpressionTree<U,T> {
    private Node<U,T> root;
    private Class<U> uClazz = null;
    private Class<T> tClazz = null;

    public ExpressionTree(Class<U> uClazz,Class<T> tClazz){
        this.uClazz = uClazz;
        this.tClazz = tClazz;
    }

    private ExpressionTree(Class<U> uClazz,Class<T> tClazz,Node<U,T> root){
        this.uClazz = uClazz;
        this.tClazz = tClazz;
        this.root = root;
    }
    /**
     * 非递归先序遍历  中左右
     * 1. 栈里压入根节点
     * 2. 结束条件 栈为空的时候
     * 3. 弹栈
     * 4. 输出当前节点值
     * 5. 右孩子压入栈中
     * 6. 左孩子压入栈中
     * 7. 判断栈是否为空 重复 3
     */
    public void preOrderTraversal(){
        Stack<Node<U,T>> exStack = new Stack<>();
        exStack.push(root);
        while(exStack.size()>0){
            Node<U, T> pop = exStack.pop();
            pop.print();
            Node<U, T> rightChild = pop.getRightChild();
            if (rightChild!=null) {
                exStack.push(rightChild);
            }
            Node<U, T> leftChild = pop.getLeftChild();
            if (leftChild!=null) {
                exStack.push(leftChild);
            }
        }
    }

    /**
     * 非递归中序遍历  左中右
     * 1. 需要一个引用和一个栈 引用指向根
     * 2. 引用为空 且 栈为空 结束
     * 3. 引用的左孩子不为空
     * 4. 此节点入栈，引用指向左孩子
     * 5. 如果引用的左孩子不为空 重复4
     * 6. 输出此节点的值
     * 7. 引用指向右孩子
     * 8. 引用是空 9  引用不是空13
     * 9. 栈大小为0        直接结束
     * 10， 栈大小不为0 弹出节点
     * 11. 输出节点 引用等于弹出节点的右孩子
     * 12. 重复8
     * 13. 重复3
     */
    public void inOrderTraversal(){
        Stack<Node<U,T>> exStack = new Stack<>();
        Node<U,T> tempNode = root;
        while(exStack.size()>0 || tempNode!=null){
            Node<U, T> leftChild = tempNode.getLeftChild();
            while (leftChild != null){
                exStack.push(tempNode);
                tempNode = leftChild;
                leftChild = tempNode.getLeftChild();
            }
            tempNode.print();
            Node<U, T> rightChild = tempNode.getRightChild();
            tempNode = rightChild;
            while (tempNode == null){
                if (exStack.size()==0) {
                    break;
                }
                Node<U, T> pop = exStack.pop();
                if (pop == null) {
                    break;
                }
                pop.print();
                tempNode = pop.getRightChild();
            }
        }
    }
    public void postOrderRecursionTraversal(){
        this.postOrderRecursionTraversal(root);
    }
    public void postOrderRecursionTraversal(Node<U,T> root){
        if(root == null){
            return;
        }
        postOrderRecursionTraversal(root.getLeftChild());
        postOrderRecursionTraversal(root.getRightChild());
        root.print();
    }

    /**
     * 非递归后序遍历
     */
    public void postOrderTraversal(){
        Stack<Node<U,T>> okLeftStack = new Stack<>();
        Stack<Node<U,T>> oKRightStack = new Stack<>();
        Node<U,T> tempNode = root;
        while(okLeftStack.size() > 0 && tempNode != null){
            while (tempNode.leftChild != null) {
                okLeftStack.push(tempNode);
                tempNode = tempNode.leftChild;
            }
            if(tempNode.rightChild != null){
                oKRightStack.push(tempNode);
                tempNode = tempNode.rightChild;
                continue;
            }
            tempNode.print();
            if(oKRightStack.size()>0){
                tempNode = oKRightStack.pop();
            }
        }

    }

    public ExpressionTree<U,T> listToExpressionTree(List expressions,boolean replaceRoot){
        Stack<Node<U,T>> exStack = new Stack<>();
        for (Object expression : expressions) {
            Node<U, T> node = new Node<>();
            if (tClazz.isInstance(expression)) {
                node.setType(Type.OPERAND);
                node.setOperand((T)expression);
                node.setLeftChild(null);
                node.setRightChild(null);
            }
            if (uClazz.isInstance(expression)) {
                Node<U, T> rightNode = (Node<U,T>)exStack.pop();
                Node<U, T> leftNode = (Node<U,T>)exStack.pop();
                node.setType(Type.OPERATOR);
                node.setOperator((U)expression);
                node.setLeftChild(leftNode);
                node.setRightChild(rightNode);
            }
            exStack.push(node);
        }
        Node<U,T> root = exStack.pop();
        if(replaceRoot){
            this.root = root;
            return this;
        }
        return new ExpressionTree<U,T>(uClazz,tClazz,root);
    }

    @Data
    @ToString
    static class Node<U,T> {
        private Type type;
        private U operator;
        private T operand;
        private Node<U,T> leftChild;
        private Node<U,T> rightChild;

        public void print(){
            switch (type){
                case OPERAND:{
                    System.out.print(operand+" ");
                    break;
                }
                case OPERATOR:{
                    System.out.print(operator+" ");
                    break;
                }
                default:{
                    System.out.println();
                }
            }
        }
    }

    enum Type{
        OPERATOR(1,"liu"),OPERAND(2,"sun");

        private int age;
        private String name;

        private Type(int age,String name){
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }



    }
}

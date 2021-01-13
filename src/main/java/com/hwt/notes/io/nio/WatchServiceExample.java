package com.hwt.notes.io.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class WatchServiceExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("D:\\workSpace\\JAVA\\mywork\\codenote\\src\\main\\resources");
        Path path = file.toPath();
        WatchService watchService = FileSystems.getDefault().newWatchService();
        path.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);
        while(true){
            WatchKey key = watchService.take();
            for (WatchEvent<?> pollEvent : key.pollEvents()) {
                WatchEvent.Kind<?> kind = pollEvent.kind();
                Object context = pollEvent.context();
                System.out.println("产生的事件为:"+kind+"上下文为："+context);
            }
            key.reset();
            boolean valid = key.isValid();
            if(!valid){
                break;
            }
        }

    }
}

package com.hwt.javacv;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;

import java.io.File;

public class Test {
    public static final String filePath = "D:\\workSpace\\tool\\高级Redis应用进阶课 一站式Redis解决方案";

    public static void main(String[] args) throws FrameGrabber.Exception {
        File file = new File(filePath);
        FFmpegFrameGrabber ffmpegFrameGrabber = new FFmpegFrameGrabber(file);
        ffmpegFrameGrabber.start();
        int lengthInFrames = ffmpegFrameGrabber.getLengthInFrames();
        int i = 0;
        while (i < lengthInFrames){
            i++;
        }
    }
}

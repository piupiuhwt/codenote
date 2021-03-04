package com.hwt.javacv;

import org.bytedeco.javacv.FFmpegFrameFilter;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;

import java.io.File;

public class Test {
    public static final String filePath = "D:\\workSpace\\tool\\高级Redis应用进阶课 一站式Redis解决方案";

    public static void main(String[] args) {
        File file = new File(filePath);
        FFmpegFrameGrabber ffmpegFrameGrabber = new FFmpegFrameGrabber(file);
        int lengthInFrames = ffmpegFrameGrabber.getLengthInFrames();
    }
}

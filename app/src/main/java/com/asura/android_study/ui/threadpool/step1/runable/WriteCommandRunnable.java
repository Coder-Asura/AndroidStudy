package com.asura.android_study.ui.threadpool.step1.runable;


import com.asura.android_study.ui.threadpool.step1.CommandQueue;

/**
 * Created by Liuxd on 2016/10/21 22:03.
 */

public class WriteCommandRunnable implements Runnable {

    @Override
    public void run() {
        CommandQueue.getInstance().takeMsg();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

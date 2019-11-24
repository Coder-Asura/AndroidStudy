package com.asura.android_study.activity.threadpool.step1;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Liuxd on 2016/10/21 22:08.
 * 命令队列
 */

public class CommandQueue {
    private BlockingQueue<String> readySendDatas;
    private static CommandQueue sCommandQueue;

    public static CommandQueue getInstance() {
        if (sCommandQueue == null) {
            synchronized (CommandQueue.class) {
                if (sCommandQueue == null) {
                    sCommandQueue = new CommandQueue();
                }
            }
        }
        return sCommandQueue;
    }

    private CommandQueue() {
        if (readySendDatas == null) {
            readySendDatas = new ArrayBlockingQueue<String>(10);
        }
    }

    /**
     * 插入命令
     *
     * @param msg
     * @return
     */
    public synchronized boolean offerMsg(String msg) {
        return !(msg == null || readySendDatas == null) && readySendDatas.offer(msg);
    }

    /**
     * 取出命令
     *
     * @return
     */
    public String takeMsg() {
        String msg = "";
        if (readySendDatas != null)
            try {
                msg = readySendDatas.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        Log.i("lxd",Thread.currentThread().getId()+ "takeMsg()==" + msg);
        return msg;
    }

    public void clearQueue() {
        if (readySendDatas != null)
            readySendDatas.clear();
    }
}

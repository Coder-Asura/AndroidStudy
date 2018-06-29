package com.asura.android_study.activity.threadpool.runable

import android.util.Log

/**
 * @author Created by Asura on 2018/6/29 16:02.
 */
class MyRunnable:Runnable {
    override fun run() {
        for (i: Int in 0..10) {
            Log.d("asura", "MyRunnable: threadName:" + Thread.currentThread().name + "  i=$i");
        }
    }
}
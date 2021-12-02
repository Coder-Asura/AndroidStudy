package com.asura.android_study.ui.threadpool.thread

import android.util.Log

/**
 * @author Created by Asura on 2018/6/29 16:00.
 */
class MyThread : Thread() {
    override fun run() {
        super.run()
        for (i: Int in 0..10) {
            Log.d("asura", "Mythread: threadName:" + Thread.currentThread().name + "  i=$i");
        }
    }
}
package com.asura.android_study.activity.threadpool.runable

import android.util.Log

/**
 * @author Created by Asura on 2018/6/29 16:43.
 */
class MyRunnableSafe : Runnable {
    private var imgNum = 9;//图片数量
    override fun run() {
        //在数据改变的地方加上同步标志 synchronized
        while (imgNum >= 0) {
            Thread.sleep(1000)
            synchronized(MyRunnableSafe::class.java) {
                Log.d("asura", Thread.currentThread().getName() + "正在上传图片...，还剩" + --imgNum + "张图片未上传")
            }
        }
    }
}
package com.asura.jsoup_demo

import android.app.Application
import com.asura.jsoup_demo.util.ALog

/**
 * @author Created by Asura on 2018/6/7 14:34.
 */
class MyApplication : Application() {

    companion object {
        lateinit var INSTANCE: MyApplication;
        public fun getInstance(): MyApplication? {
            return INSTANCE;
        }
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this;
        ALog.initLog();
    }
}
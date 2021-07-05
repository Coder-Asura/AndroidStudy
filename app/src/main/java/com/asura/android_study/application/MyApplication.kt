package com.asura.android_study.application

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

import com.asura.a_log.ALog
import com.asura.android_study.BuildConfig

/**
 * Created by Liuxd on 2016/9/14 14:22.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ALog.initLog(BuildConfig.LOG_ENABLE, "Asura")
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}

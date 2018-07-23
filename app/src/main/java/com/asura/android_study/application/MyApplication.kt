package com.asura.android_study.application

import android.app.Application

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
}

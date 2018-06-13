package com.asura.android_study.application;

import android.app.Application;

import com.asura.android_study.BuildConfig;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by Liuxd on 2016/9/14 14:22.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("asura").logLevel(BuildConfig.LOG_ENABLE ? LogLevel.FULL : LogLevel.NONE)
                .methodCount(1)
                .methodOffset(2);
    }
}

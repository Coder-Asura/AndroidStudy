package com.asura.customview_study.application;

import android.app.Application;

import com.asura.customview_study.utils.ALog;

/**
 * Created by Liuxd on 2016/9/14 14:22.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ALog.INSTANCE.initLog();
    }
}

package com.asura.greendao_study.application;

import android.app.Application;

/**
 * Created by Liuxd on 2016/9/7 17:58.
 */
public class MyApplication extends Application {
    private static MyApplication mMyApplication;

    public MyApplication() {
    }

    public static MyApplication getApplication() {
        return mMyApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMyApplication = this;
    }
}

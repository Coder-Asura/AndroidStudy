package com.lxd.common_app;

import android.app.Application;

import com.lxd.common_app.utils.LogUtil;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Liuxd on 2016/9/23 13:18.
 * 自定义Application
 * <p>
 * 完成一些初始化操作(内存泄漏、日志、崩溃日志)
 * </p>
 */

public class App extends Application {
    private static App INSTANCE;

    public static App getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化LeakCanary内存泄漏检测
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
        INSTANCE = this;
        //初始化Logger日志输出
        LogUtil.resetSetting();
        //初始化Bugly崩溃反馈,也可以在清单文件里配置
//        CrashReport.initCrashReport(INSTANCE, "900055114", BuildConfig.BUGLY_ENABLE_DEBUG);
    }

}

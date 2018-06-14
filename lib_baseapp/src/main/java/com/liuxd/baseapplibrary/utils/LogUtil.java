package com.liuxd.baseapplibrary.utils;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by Liuxd on 2016/11/9 8:52.
 * 封装Logger，实现简单的日志打印以及json、xml格式的输出
 */

public class LogUtil {
    private static final String TAG = "LogUtil";

    public static void i(String msg, Object... args) {
        Logger.i(msg, args);
    }

    public static void d(String msg, Object... args) {
        Logger.d(msg, args);
    }

    public static void w(String msg, Object... args) {
        Logger.w(msg, args);
    }

    public static void e(String msg, Object... args) {
        Logger.e(msg, args);
    }

    public static void e(Throwable throwable, String msg, Object... args) {
        Logger.e(throwable, msg, args);
    }

    public static void wtf(String msg, Object... args) {
        Logger.wtf(msg, args);
    }

    public static void json(String json) {
        Logger.json(json);
    }

    public static void xml(String xml) {
        Logger.xml(xml);
    }

    public static void i(String tag, String msg, Object... args) {
        Logger.t(tag).i(msg, args);
    }

    public static void d(String tag, String msg, Object... args) {
        Logger.t(tag).d(msg, args);
    }

    public static void w(String tag, String msg, Object... args) {
        Logger.t(tag).w(msg, args);
    }

    public static void e(String tag, String msg, Object... args) {
        Logger.t(tag).e(msg, args);
    }

    public static void e(String tag, Throwable throwable, String msg, Object... args) {
        Logger.t(tag).e(throwable, msg, args);
    }

    public static void wtf(String tag, String msg, Object... args) {
        Logger.t(tag).wtf(msg, args);
    }

    public static void json(String tag, String json) {
        Logger.t(tag).json(json);
    }

    public static void xml(String tag, String xml) {
        Logger.t(tag).xml(xml);
    }

    public static void resetSetting() {
        Logger.init(LogUtil.TAG).logLevel( LogLevel.FULL )
                .methodCount(1).methodOffset(2);
    }
}

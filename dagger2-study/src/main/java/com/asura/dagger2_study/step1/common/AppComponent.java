package com.asura.dagger2_study.step1.common;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Created by Asura on 2018/5/31 15:27.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    /**
     * 获取 MyApplication 实例
     *
     * @return MyApplication 实例
     */
    MyApplication myApplication();

    /**
     * 获取 SharedPreferences 实例
     *
     * @return SharedPreferences 实例
     */
    SharedPreferences sharedPreferences();
}

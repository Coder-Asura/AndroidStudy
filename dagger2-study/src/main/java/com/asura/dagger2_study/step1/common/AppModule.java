package com.asura.dagger2_study.step1.common;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Created by Asura on 2018/5/31 15:23.
 */
@Module
public class AppModule {
    private MyApplication myApplication;

    public AppModule(MyApplication application) {
        this.myApplication = application;
    }

    //提供全局的sp对象
    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences() {
        return myApplication.getSharedPreferences("spfile", Context.MODE_PRIVATE);
    }

    //提供全局的Application对象
    @Singleton
    @Provides
    MyApplication provideApplication() {
        return myApplication;
    }

}

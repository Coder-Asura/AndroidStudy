package com.asura.dagger2_android_study.dagger.module;

import com.asura.dagger2_android_study.bean.Student;
import com.asura.dagger2_android_study.dagger.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author Created by Asura on 2018/6/4 17:26.
 */
@Module
public class MainActivityModule {
    @ActivityScope
    @Provides
    static Student provideStudent() {
        return new Student();
    }
}

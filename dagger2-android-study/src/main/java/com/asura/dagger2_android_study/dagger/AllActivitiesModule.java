package com.asura.dagger2_android_study.dagger;

import com.asura.dagger2_android_study.MainActivity;
import com.asura.dagger2_android_study.dagger.module.MainActivityModule;
import com.asura.dagger2_android_study.dagger.scope.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Created by Asura on 2018/6/4 17:30.
 */
@Module
public abstract class AllActivitiesModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributeMainActivityInjector();
}

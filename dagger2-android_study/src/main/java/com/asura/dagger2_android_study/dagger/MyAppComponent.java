package com.asura.dagger2_android_study.dagger;

import com.asura.dagger2_android_study.base.MyApplication;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author Created by Asura on 2018/6/4 17:28.
 */
@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AllActivitiesModule.class})
public interface MyAppComponent {

    void inject(MyApplication myApplication);
}

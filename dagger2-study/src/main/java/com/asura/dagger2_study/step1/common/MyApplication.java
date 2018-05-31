package com.asura.dagger2_study.step1.common;

import android.app.Application;

/**
 * @author Created by Asura on 2018/5/31 15:22.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        inject();
    }

    private void inject() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        ComponentHolder.setAppComponent(appComponent);
    }
}

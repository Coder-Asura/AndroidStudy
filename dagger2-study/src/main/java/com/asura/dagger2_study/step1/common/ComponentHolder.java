package com.asura.dagger2_study.step1.common;

/**
 * @author Created by Asura on 2018/5/31 15:36.
 */
public class ComponentHolder {
    private static AppComponent sAppComponent;

    public static void setAppComponent(AppComponent appComponent) {
        sAppComponent = appComponent;
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}

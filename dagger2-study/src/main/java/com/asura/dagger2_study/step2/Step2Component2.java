package com.asura.dagger2_study.step2;

import com.asura.dagger2_study.step1.common.AppComponent;

import dagger.Component;

/**
 * @author Created by Asura on 2018/5/31 16:17.
 */
@ActivityScope
@Component(modules = Step2Module2.class, dependencies = AppComponent.class)
public interface Step2Component2 {

    void inject(Step2Activity2 activity);
}

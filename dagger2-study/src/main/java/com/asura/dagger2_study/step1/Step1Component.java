package com.asura.dagger2_study.step1;

import com.asura.dagger2_study.step1.common.AppComponent;

import dagger.Component;

/**
 * @author Created by Asura on 2018/5/31 16:17.
 */
@Component(modules = Step1Module.class, dependencies = AppComponent.class)
public interface Step1Component {

    void inject(Step1Activity activity);
}

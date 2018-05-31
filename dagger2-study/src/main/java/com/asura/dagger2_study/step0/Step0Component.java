package com.asura.dagger2_study.step0;

import dagger.Component;

/**
 * @author Created by Asura on 2018/5/31 14:06.
 */
//Component接口上方的@Component(modules = A01SimpleModule.class)说明这是一个【组件】（我更喜欢称呼它为注射器）
@Component(modules = Step0Module.class)
public interface Step0Component {
    void inject(Step0Activity step0Activity);
}

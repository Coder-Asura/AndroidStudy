package com.asura.dagger2_study.step0;

import dagger.Module;

/**
 * @author Created by Asura on 2018/5/31 14:01.
 */
//Module类上方的@Module注解意味着这是一个提供数据的【模块】
@Module
public class Step0Module {
    private Step0Activity mStep0Activity;

    public Step0Module(Step0Activity step0Activity) {
        this.mStep0Activity = step0Activity;
    }

//    @Provides
//    Student providerStudent() {
//        return new Student();
//    }
}

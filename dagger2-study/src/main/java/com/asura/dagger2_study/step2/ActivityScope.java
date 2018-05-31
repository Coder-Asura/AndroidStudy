package com.asura.dagger2_study.step2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * 局部单例
 *
 * @author Created by Asura on 2018/5/31 18:07.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}

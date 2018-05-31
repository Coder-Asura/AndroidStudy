package com.asura.dagger2_study.step1;

import dagger.Module;
import dagger.Provides;

/**
 * @author Created by Asura on 2018/5/31 16:13.
 */
@Module
public class Step1Module {
    private Step1Activity mActivity;

    public Step1Module(Step1Activity activity) {
        this.mActivity = activity;
    }

    @Provides
  static   GirlFriend providerGirlFriend() {
        return new GirlFriend();
    }
}

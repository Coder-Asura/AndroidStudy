package com.asura.android_study.utils;

import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;


/**
 * Created by Liuxd on 2016/9/21 15:07.
 */

public class RxCountDown {

    public static Observable<Integer> countdown(final int start, final int end) {

        final long time = 500 / Math.abs(end - start);

        return Observable.interval(0, time, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Long, Integer>() {

                    @Override
                    public Integer apply(Long increaseTime) {
                        Logger.d("increaseTime" + increaseTime);
                        if ((end - start) > 0) {
                            return end - increaseTime.intValue() - 1;
                        } else {
                            return start + increaseTime.intValue() + 1;
                        }
                    }
                }).take(Math.abs(end - start));
    }
}

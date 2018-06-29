package com.asura.android_study.activity.threadpool.callable

import java.util.concurrent.Callable

/**
 * @author Created by Asura on 2018/6/29 16:05.
 */
class MyCallable : Callable<Int> {
    override fun call(): Int {
        var sum: Int = 0
        for (i: Int in 0..10) {
            sum += i
        }
        return sum;
    }
}
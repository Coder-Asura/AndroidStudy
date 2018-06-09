package com.asura.jsoup_demo.base

/**
 * 网络请求回调接口
 *
 * Created by Liuxd on 2018/6/9 15:30.
 */
interface HttpCallback<T> {

    fun onSuccess(data: T)

    fun onFailed(msg: String?, e: Throwable)
}
package com.asura.jsoup_demo.mvp

import java.lang.ref.WeakReference
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * Presenter基类
 */
open abstract class BasePresenter<V : IView> : IPresenter<V> {

    var weakView: WeakReference<V>? = null
    var proxy: V? = null

    fun getView(): V {
        return this.proxy!!
    }

    override fun attachView(v: V) {
        this.weakView = WeakReference(v)

        val invocationHandler = MvpViewInvocationHandler(this.weakView!!.get()!!)
        // 在这里采用动态代理
        proxy = Proxy.newProxyInstance(
                v.javaClass.classLoader, v.javaClass.interfaces, invocationHandler) as V
    }

    override fun detachView() {
        weakView = null
    }

    /**
     * 用于检查View是否为空对象
     *
     * @return
     */
    fun isAttachView(): Boolean {
        return this.weakView != null && this.weakView!!.get() != null
    }

    private inner class MvpViewInvocationHandler(private val iView: IView) : InvocationHandler {

        @Throws(Throwable::class)
        override fun invoke(arg0: Any, arg1: Method, arg2: Array<Any>): Any? {
            return if (isAttachView()) {
                arg1.invoke(iView, *arg2)
            } else null
        }

    }
}
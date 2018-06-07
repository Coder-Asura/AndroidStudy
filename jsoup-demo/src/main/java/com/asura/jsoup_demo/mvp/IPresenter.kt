package com.asura.jsoup_demo.mvp

/**
 * Presenter接口
 */
interface IPresenter<V : IView> {

    /**
     * 绑定视图
     */
    fun attachView(v: V)

    /**
     * 解绑视图
     */
    fun detachView()
}
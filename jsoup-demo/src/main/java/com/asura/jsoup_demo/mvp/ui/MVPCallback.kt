package com.asura.jsoup_demo.mvp.ui

import com.asura.jsoup_demo.mvp.IPresenter
import com.asura.jsoup_demo.mvp.IView

/**
 * MVP代理
 */
interface MVPCallback<V : IView, P : IPresenter<V>> {

    /**
     * 创建Presenter
     */
    fun createPresenter(): P

    /**
     * 获取Presenter
     */
    fun getPresenter(): P

    /**
     * 设置Presenter
     */
    fun setPresenter(p: P?)

    /**
     * 获取View接口
     */
    fun getMVPView(): V

}
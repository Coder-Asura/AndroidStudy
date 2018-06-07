package com.asura.jsoup_demo.mvp.ui

import com.asura.jsoup_demo.mvp.IPresenter
import com.asura.jsoup_demo.mvp.IView

/**
 * MVP生命周期代理
 */
class MVPCallbackImp<V : IView, P : IPresenter<V>> : MVPCallback<V, P> {

    private var callback: MVPCallback<V, P>? = null

    constructor(callback: MVPCallback<V, P>) {
        this.callback = callback
    }

    override fun createPresenter(): P {
        val p: P = callback!!.createPresenter()
        setPresenter(p)
        return getPresenter()
    }

    override fun getPresenter(): P {
        return callback!!.getPresenter()
    }

    override fun setPresenter(p: P?) {
        callback!!.setPresenter(p)
    }

    override fun getMVPView(): V {
        return callback!!.getMVPView()
    }

    /**
     * 绑定View
     */
    fun attach() {
        callback!!.getPresenter().attachView(callback!!.getMVPView())
    }

    /**
     * 解绑View
     */
    fun detach() {
        callback!!.getPresenter().detachView()
        callback!!.setPresenter(null)
    }
}
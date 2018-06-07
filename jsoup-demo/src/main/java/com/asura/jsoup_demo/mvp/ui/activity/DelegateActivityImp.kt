package com.asura.jsoup_demo.mvp.ui.activity

import com.asura.jsoup_demo.mvp.IPresenter
import com.asura.jsoup_demo.mvp.IView
import com.asura.jsoup_demo.mvp.ui.MVPCallback
import com.asura.jsoup_demo.mvp.ui.MVPCallbackImp

/**
 * Activity代理
 */
class DelegateActivityImp<V : IView, P : IPresenter<V>> : DelegateActivity {

    private var callback: MVPCallbackImp<V, P>? = null

    constructor(callback: MVPCallback<V, P>) {
        this.callback = MVPCallbackImp(callback)
    }

    override fun onCreate() {
        callback!!.createPresenter()
        callback!!.attach()
    }

    override fun onDestroy() {
        callback!!.detach()
    }

}
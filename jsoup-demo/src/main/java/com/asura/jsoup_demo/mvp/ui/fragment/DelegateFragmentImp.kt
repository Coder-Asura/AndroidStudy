package com.asura.jsoup_demo.mvp.ui.fragment

import com.asura.jsoup_demo.mvp.IPresenter
import com.asura.jsoup_demo.mvp.IView
import com.asura.jsoup_demo.mvp.ui.MVPCallback
import com.asura.jsoup_demo.mvp.ui.MVPCallbackImp

/**
 * Fragment生命周期代理
 */
class DelegateFragmentImp<V : IView, P : IPresenter<V>> : DelegateFragment {

    private var callback: MVPCallbackImp<V, P>? = null

    constructor(callback: MVPCallback<V, P>) {
        this.callback = MVPCallbackImp(callback)
    }

    override fun onCreateView() {
        callback!!.createPresenter()
        callback!!.attach()
    }

    override fun onDestroy() {
        callback!!.detach()
    }
}
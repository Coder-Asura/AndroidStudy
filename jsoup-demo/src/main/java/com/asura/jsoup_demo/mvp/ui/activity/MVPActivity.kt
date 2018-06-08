package com.asura.jsoup_demo.mvp.ui.activity

import android.os.Bundle
import com.asura.jsoup_demo.mvp.IPresenter
import com.asura.jsoup_demo.mvp.IView
import com.asura.jsoup_demo.mvp.ui.MVPCallback
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * 集成MVP的Activity
 */
open abstract class MVPActivity<V : IView, P : IPresenter<V>> : RxAppCompatActivity(), IView, MVPCallback<V, P> {

    private var p: P? = null
    private var delegateActivity: DelegateActivityImp<V, P>? = null

    private fun getActivityDelegate(): DelegateActivityImp<V, P> {
        delegateActivity = DelegateActivityImp(this)
        return delegateActivity!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        getActivityDelegate()
        delegateActivity!!.onCreate()
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        delegateActivity!!.onDestroy()
        super.onDestroy()
    }

    override fun createPresenter(): P {
        return this.p!!
    }

    override fun getPresenter(): P {
        return this.p!!
    }

    override fun setPresenter(p: P?) {
        this.p = p
    }

    override fun getMVPView(): V {
        return this as V
    }

}
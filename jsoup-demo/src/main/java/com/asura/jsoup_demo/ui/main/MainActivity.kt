package com.asura.jsoup_demo.ui.main

import android.view.View
import com.asura.jsoup_demo.R
import com.asura.jsoup_demo.base.BaseActivity

class MainActivity : BaseActivity<IMainView, MainPresenter>(), IMainView {

    override fun getLayoutId(): Int {
        return R.layout.activity_main;
    }

    override fun initViews() {

    }

    override fun onClick(v: View?) {
    }

    override fun switchContent(position: Int) {
    }

    override fun createPresenter(): MainPresenter {
        return MainPresenter()
    }

}

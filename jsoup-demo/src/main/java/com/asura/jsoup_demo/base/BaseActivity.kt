package com.asura.jsoup_demo.base

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.asura.jsoup_demo.R
import com.asura.jsoup_demo.mvp.IPresenter
import com.asura.jsoup_demo.mvp.IView
import com.asura.jsoup_demo.mvp.ui.activity.MVPActivity

/**
 * @author Created by Asura on 2018/6/7 13:13.
 */
abstract class BaseActivity<V : IView, P : IPresenter<V>> : MVPActivity<V, P>(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initActionBar()
        initViews()
        initListener()
        initDatas()
    }

    /**
     * 获取布局Id
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化监听器
     */
    fun initListener() {}

    /**
     * 初始化数据，调用位置在 initViews 之后
     */
    protected fun initDatas() {}

    /**
     * 初始化 View， 调用位置在 initDatas 之后
     */
    protected abstract fun initViews()


    /**
     * 初始化 ActionBar
     */
    private fun initActionBar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        if (toolbar != null) {
            toolbar.setTitle("")
            setSupportActionBar(toolbar)
        }
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // 默认点击左上角是结束当前 Activity
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
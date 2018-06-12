package com.asura.jsoup_demo.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.asura.jsoup_demo.R
import com.asura.jsoup_demo.util.UiUtils
import com.asura.mvp_framework.base.presenter.IMvpPresenter
import com.asura.mvp_framework.base.view.IMvpView
import com.asura.mvp_framework.support.view.BaseMvpActivity

/**
 * @author Created by Asura on 2018/6/7 13:13.
 */
abstract class BaseActivity<V : IMvpView, P : IMvpPresenter<V>> : BaseMvpActivity<V, P>(), View.OnClickListener {
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
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * 初始化监听器
     */
    open fun initListener() {}

    /**
     * 初始化数据，调用位置在 initViews 之后
     */
    open fun initDatas() {}

    /**
     * 初始化 View， 调用位置在 initDatas 之后
     */
    protected abstract fun initViews()


    /**
     * 初始化 ActionBar
     */
    private fun initActionBar() {
        var toolbar: Toolbar? = null
        try {
            toolbar = findViewById(R.id.toolbar) as Toolbar
        } catch (e: Exception) {
            e.printStackTrace()
        }
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

    /**
     * 发出一个短Toast
     *
     * @param msgId 内容
     */
    fun toastShort(@StringRes msgId: Int) {
        UiUtils.toastShort(msgId)
    }

    /**
     * 发出一个短Toast
     *
     * @param msg 内容
     */
    fun toastShort(msg: String) {
        UiUtils.toastShort(msg)
    }

    /**
     * 发出一个长toast提醒
     *
     * @param msgId 内容
     */
    fun toastLong(@StringRes msgId: Int) {
        UiUtils.toastLong(msgId)
    }

    /**
     * 发出一个长toast提醒
     *
     * @param msg 内容
     */
    fun toastLong(msg: String) {
        UiUtils.toastLong(msg)
    }
}
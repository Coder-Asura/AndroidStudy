package com.asura.jsoup_demo.base

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asura.jsoup_demo.mvp.IPresenter
import com.asura.jsoup_demo.mvp.IView
import com.asura.jsoup_demo.mvp.ui.fragment.MVPFragment
import com.asura.jsoup_demo.util.ALog
import com.asura.jsoup_demo.util.UiUtils

/**
 * @author Created by Asura on 2018/6/7 14:23.
 */
abstract class BaseFragment<V : IView, P : IPresenter<V>> : MVPFragment<V, P>() {
    protected val TAG = this.javaClass.simpleName
    var mActivity: BaseActivity<V, P>? = null

    fun getAc(): BaseActivity<V, P> {
        return this.mActivity!!;
    }

    /**
     * 获取布局id
     *
     * @return 布局id
     */
    @LayoutRes
    protected abstract fun getLayoutId(): Int

    /**
     * 初始化views
     *
     * @param rootView fragment的根布局
     */
    protected abstract fun initViews(rootView: View)

    /**
     * 初始化监听器
     */
    fun initListener() {}

    /**
     * 初始化数据，调用位置在 initViews 之前
     */
    protected fun initDatas() {}

    /**
     * 返回键
     */
    fun onBackPressed(): Boolean {
        return false
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        ALog.t("生命周期", TAG, "onAttach(Context context)")
        onAttachToContext(context)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        ALog.t("生命周期", TAG, "onAttach(Activity activity)")
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity)
        }
    }

    fun onAttachToContext(context: Context?) {
        ALog.t("生命周期", TAG, "onAttach")
        if (context is BaseActivity<*, *>) {
            mActivity = context as BaseActivity<V, P>?
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ALog.t("生命周期", TAG, "onCreate")
        mActivity = activity as BaseActivity<V, P>
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        ALog.t("生命周期", TAG, "onCreateView")
        val rootView = inflater.inflate(getLayoutId(), container, false)
        initViews(rootView)
        initListener()
        initDatas()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ALog.t("生命周期", TAG, "onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ALog.t("生命周期", TAG, "onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        ALog.t("生命周期", TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        ALog.t("生命周期", TAG, "onResume")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        ALog.t("生命周期", TAG, "onHiddenChanged hidden:$hidden")
    }

    override fun onPause() {
        super.onPause()
        ALog.t("生命周期", TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        ALog.t("生命周期", TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        ALog.t("生命周期", TAG, "onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //        DaoUtils.getUserManager().closeDataBase();
        ALog.t("生命周期", TAG, "onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        ALog.t("生命周期", TAG, "onDetach")
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
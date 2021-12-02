package com.asura.android_study.ui.base

import com.asura.a_log.ALog.d
import android.os.Build
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by Liuxd on 2016/11/14 18:38.
 *
 *
 * 通用的Fragment
 *
 * 集合了ButterKnife、沉浸式状态栏、侧滑返回、activity栈管理以及一些常用的方法
 */
abstract class BaseFragment : Fragment(), IBase {
    private var mRootView: View? = null
    private val TAG = this.javaClass.simpleName

    override fun initData() {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            (context as? Activity)?.let { onAttachToContext(it) }
        }
        onAttachToContext(context)
    }
    /**
     * 因为Api23之后的方法不一样，所以都调用这个方法
     *
     * @param context 上下文（一般都是数据交互的接口）
     */
    open fun onAttachToContext(context: Context?) {
        d(TAG, "onAttachToContext")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        d(TAG, "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        d(TAG, "onCreateView")
        if (null != mRootView) {
            val parent = mRootView!!.parent as ViewGroup
            parent?.removeView(mRootView)
        }
        mRootView = inflater.inflate(setLayoutId(), null)
        initData()
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        d(TAG, "onViewCreated")
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        d(TAG, "onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        d(TAG, "onResume")
    }


    override fun onPause() {
        super.onPause()
        d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        d(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        d(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        d(TAG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        d(TAG, "onDetach")
    }
}
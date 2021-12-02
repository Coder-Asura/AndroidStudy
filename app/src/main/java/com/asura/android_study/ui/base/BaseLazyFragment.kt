package com.asura.android_study.ui.base

import android.content.Context
import com.asura.a_log.ALog.d
import android.os.Bundle

/**
 * Created by Liuxd on 2016/11/12 15:39.
 */
abstract class BaseLazyFragment : BaseFragment() {
    val TAG = this.javaClass.simpleName
    protected var mContext: Context? = null
    protected var isUserVisible = false
    private var isPrepared = false
    private var isFirst = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity
    }



    //--------------------system method callback------------------------//
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isPrepared = true
        initPrepare()
        d(TAG + "准备数据")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            isUserVisible = true
            lazyLoad()
        } else {
            isUserVisible = false
            //            onInvisible();
        }
    }

    override fun onResume() {
        super.onResume()
        if (userVisibleHint) {
            userVisibleHint = true
        }
    }
    //--------------------------------method---------------------------//
    /**
     * 懒加载
     */
    protected fun lazyLoad() {
        if (!isPrepared || !isUserVisible || !isFirst) {
            return
        }
        initDataReal()
        d("$TAG 正式初始化")
        isFirst = false
    }

    //--------------------------abstract method------------------------//
    //    /**
    //     * 在onActivityCreated中调用的方法，可以用来进行初始化操作。
    //     */
    protected abstract fun initPrepare()
    /**
     * fragment被设置为不可见时调用
     */
    //    protected abstract void onInvisible();
    /**
     * 这里获取数据，刷新界面
     */
    protected abstract fun initDataReal()

    /**
     * 初始化布局，请不要把耗时操作放在这个方法里，这个方法用来提供一个
     * 基本的布局而非一个完整的布局，以免ViewPager预加载消耗大量的资源。
     */
//    abstract fun setLayoutId(): Int
}
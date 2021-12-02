package com.asura.android_study.ui.base

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.asura.a_log.ALog
import com.asura.android_study.R
import com.asura.android_study.ui.main.MainActivity
import com.asura.android_study.utils.AppManager
import com.asura.android_study.utils.ToastUtil
import com.jaeger.library.StatusBarUtil
import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrConfig
import com.r0adkll.slidr.model.SlidrPosition

/**
 * Created by Liuxd on 2016/11/8 15:35.
 *
 *
 * 通用的Activity
 *
 * 集合了沉浸式状态栏、侧滑返回、activity栈管理以及一些常用的方法
 */
abstract class BaseActivity : AppCompatActivity(), IBase {
    val TAG = this.javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ALog.d(TAG, "onCreate")
        setContentView(setLayoutId()) //设置布局文件
        setStatusBarColor(resources.getColor(R.color.colorPrimary), 0) //设置状态栏颜色
        initSlidrConfig() //初始化滑动返回
        AppManager.getInstance().addActivity(this)
        initToolBar()
        initData()
        initView() //一些初始化操作
    }

    override fun initData() {

    }

    private fun initToolBar() {}
    private fun initSlidrConfig() {
        val config = SlidrConfig.Builder() //                .primaryColor(getResources().getColor(R.color.colorPrimary))//滑动时状态栏的渐变结束的颜色
            .position(SlidrPosition.LEFT) //从左边滑动
            //                .sensitivity(1f)
            //                .scrimColor(Color.BLACK)
            //                .scrimStartAlpha(0.8f)//滑动开始时两个Activity之间的透明度
            //                .scrimEndAlpha(0f)//滑动结束时两个Activity之间的透明度
            .velocityThreshold(4800f) //超过这个滑动速度，忽略位移限定值就切换Activity
            .distanceThreshold(0.5f) //滑动位移占屏幕的百分比，超过这个间距就切换Activity
            .edge(true) //只能从边上开始滑动
            //                .edgeSize(0.3f) // The % of the screen that counts as the edge, default 18%
            //              .listener(new SlidrListener(){...})
            .build()
        if (this is MainActivity) {
            Slidr.attach(this, config).lock()
        } else {
            Slidr.attach(this, config)
        }
    }

    fun setStatusBarColor(color: Int, alpha: Int) {
        StatusBarUtil.setColor(this, color, alpha)
    }

    fun setSwipeBackSupport(support: Boolean) {
        if (support) {
            Slidr.attach(this).unlock()
        } else {
            Slidr.attach(this).lock()
        }
    }

    fun showToast(msgId: Int) {
        ToastUtil.getInstance().Short(getString(msgId)).show()
    }

    fun showToast(msg: String?) {
        ToastUtil.getInstance().Short(msg).show()
    }

    override fun onStart() {
        super.onStart()
        ALog.d(TAG, "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        ALog.d(TAG, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        ALog.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        // 跳转时隐藏键盘
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        val view = currentFocus
        if (imm != null) {
            if (view != null && view is EditText) {
                imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            }
        }
        ALog.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        ALog.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.getInstance().removeActivity(this)
        ALog.d(TAG, "onDestroy")
    }
}
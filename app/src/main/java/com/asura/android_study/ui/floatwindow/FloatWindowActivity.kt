package com.asura.android_study.ui.floatwindow

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.asura.android_study.R
import com.asura.android_study.ui.floatwindow.service.SuspendwindowService
import com.asura.android_study.ui.floatwindow.utils.ItemViewTouchListener
import com.asura.android_study.ui.floatwindow.utils.Utils
import com.asura.android_study.ui.floatwindow.utils.Utils.REQUEST_FLOAT_CODE
import com.asura.android_study.ui.floatwindow.utils.ViewModleMain
import kotlinx.android.synthetic.main.activity_float_window.*

/**
 * 作者：AndroidLMY
 * 链接：https://juejin.cn/post/6951608145537925128
 * 来源：稀土掘金
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class FloatWindowActivity : AppCompatActivity(), View.OnClickListener {
    private var floatRootView: View? = null //悬浮窗View
    private var isReceptionShow = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_float_window)
        startService(Intent(this, SuspendwindowService::class.java))
        bt_01.setOnClickListener(this)
        bt_02.setOnClickListener(this)
        bt_03.setOnClickListener(this)
        bt_04.setOnClickListener(this)
        bt_05.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            bt_01 -> {
                showCurrentWindow()
            }
            bt_02 -> {
                Utils.checkSuspendedWindowPermission(this) {
                    isReceptionShow = false
                    ViewModleMain.isShowSuspendWindow.postValue(true)
                }
            }
            bt_03 -> {
                Utils.checkAccessibilityPermission(this) {
                    ViewModleMain.isShowWindow.postValue(true)
                }
            }
            bt_04 -> {
                Utils.checkSuspendedWindowPermission(this) {
                    isReceptionShow = true
                    ViewModleMain.isShowSuspendWindow.postValue(true)
                }
            }
            bt_05 -> {
                closeAllSuspendWindow()
            }
        }
    }

    /**
     * 应用界面内显示悬浮球
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun showCurrentWindow() {
        var layoutParam = WindowManager.LayoutParams().apply {
            //设置大小 自适应
            width = WRAP_CONTENT
            height = WRAP_CONTENT
            flags =
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        }
        // 新建悬浮窗控件
        floatRootView = LayoutInflater.from(this).inflate(R.layout.activity_float_item, null)
        //设置拖动事件
        floatRootView?.setOnTouchListener(ItemViewTouchListener(layoutParam, windowManager))
        // 将悬浮窗控件添加到WindowManager
        windowManager.addView(floatRootView, layoutParam)
    }


    /**
     * 关闭所有悬浮窗
     */
    fun closeAllSuspendWindow() {
        if (!Utils.isNull(floatRootView)) {
            if (!Utils.isNull(floatRootView?.windowToken)) {
                if (!Utils.isNull(windowManager)) {
                    windowManager?.removeView(floatRootView)
                }
            }
        }
        ViewModleMain.isShowSuspendWindow.postValue(false)
        ViewModleMain.isShowWindow.postValue(false)
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_FLOAT_CODE) {
            if (Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "悬浮窗权限已经打开", Toast.LENGTH_SHORT).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResume() {
        super.onResume()
        if (isReceptionShow) {
            ViewModleMain.isVisible.postValue(true)
        }
    }

    override fun onStop() {
        super.onStop()
        if (isReceptionShow) {
            ViewModleMain.isVisible.postValue(false)
        }
    }

}
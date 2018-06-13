package com.asura.jsoup_study.util

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.text.TextUtils
import android.util.DisplayMetrics
import android.widget.TextView
import com.asura.jsoup_study.MyApplication

/**
 * UI 工具类
 *
 * @author Created by Asura on 2018/06/07 10:32.
 */
object UiUtils {

    /**
     * 判断TextView的输入是否为空
     *
     * @param textView
     * @return true/false
     */
    fun isEmpty(textView: TextView): Boolean {
        return TextUtils.isEmpty(textView.text.toString().trim { it <= ' ' })
    }

    /**
     * 判断string是否为空
     *
     * @param string
     * @return true/false
     */
    fun isEmpty(string: String): Boolean {
        return TextUtils.isEmpty(string)
    }

    /**
     * @param src
     * @return 如果src不能转，则默认返回-1
     */
    fun stringToInt(src: String): Int {
        var res = -1
        try {
            res = Integer.parseInt(src)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return res
    }

    fun stringToLong(src: String): Long {
        var res: Long = -1
        try {
            res = java.lang.Long.parseLong(src)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return res
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param context （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue sp
     * @param context （DisplayMetrics类中属性scaledDensity）
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * @param context 上下文
     * @return 屏幕的宽
     */
    fun getWindowWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    /**
     * @param context 上下文
     * @return 屏幕的高
     */
    fun getWindowHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    /**
     * 获取drawable res文件
     */
    fun getDrawable(@DrawableRes resId: Int): Drawable {
        return MyApplication.getInstance()!!.resources.getDrawable(resId)
    }

    /**
     * 获取color
     */
    fun getColor(@ColorRes resId: Int): Int {
        return MyApplication.getInstance()!!.resources.getColor(resId)
    }

    fun getString(@StringRes resId: Int): String {
        return if (resId == -1) {
            ""
        } else MyApplication.getInstance()!!.resources.getString(resId)
    }

    fun getUrl(url: String, params: Map<String, String>?): String {
        var url = url
        // 添加url参数
        if (params != null) {
            val it = params.keys.iterator()
            var sb: StringBuffer? = null
            while (it.hasNext()) {
                val key = it.next()
                val value = params[key]
                if (sb == null) {
                    sb = StringBuffer()
                    sb.append("?")
                } else {
                    sb.append("&")
                }
                sb.append(key)
                sb.append("=")
                sb.append(value)
            }
            if (sb != null) {
                url += sb.toString()
            }
        }
        return url
    }

    /**
     * 弹出短暂(2s)Toast提示
     *
     * @param msg 提示语
     */
    fun toastShort(msg: String) {
        ToastUtils.instance.toastShort(MyApplication.getInstance()!!, msg).show()
    }

    /**
     * 弹出短暂(2s)Toast提示
     *
     * @param resId 提示语资源id
     */
    fun toastShort(@StringRes resId: Int) {
        ToastUtils.instance.toastShort(MyApplication.getInstance()!!, getString(resId)).show()
    }

    /**
     * 弹出长时间（3.5s）Toast提示
     *
     * @param msg 提示语
     */
    fun toastLong(msg: String) {
        ToastUtils.instance.toastLong(MyApplication.getInstance()!!, msg).show()
    }

    /**
     * 弹出长时间（3.5s）Toast提示
     *
     * @param resId 提示语资源id
     */
    fun toastLong(@StringRes resId: Int) {
        ToastUtils.instance.toastLong(MyApplication.getInstance()!!, getString(resId)).show()
    }

    /**
     * 判断phone导航栏是否存在
     *
     * @param activity
     * @return
     */
    fun isNavigationBarExist(activity: Activity): Boolean {
        val windowManager = activity.windowManager
        val d = windowManager.defaultDisplay

        val realDisplayMetrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            d.getRealMetrics(realDisplayMetrics)
        }

        val realHeight = realDisplayMetrics.heightPixels
        val realWidth = realDisplayMetrics.widthPixels

        val displayMetrics = DisplayMetrics()
        d.getMetrics(displayMetrics)

        val displayHeight = displayMetrics.heightPixels
        val displayWidth = displayMetrics.widthPixels

        return realWidth - displayWidth > 0 || realHeight - displayHeight > 0
    }
}

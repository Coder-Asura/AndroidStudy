package com.asura.jsoup_demo.util

import android.content.Context
import android.widget.Toast

/**
 * 自定义Toast工具类
 *
 * 支持取消之前的Toast，直接修改信息
 *
 * @author Created by Liuxd on 2018/06/07 10:15.
 */
class ToastUtils private constructor() {

    /**
     * 获取Toast
     *
     * @return
     */
    var toast: Toast? = null
        private set

    /**
     * 短时间显示Toast
     */
    fun toastShort(context: Context, message: CharSequence): ToastUtils {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(message)
            toast!!.duration = Toast.LENGTH_SHORT
        }
        return this
    }

    /**
     * 长时间显示Toast
     */
    fun toastLong(context: Context, message: CharSequence): ToastUtils {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        } else {
            toast!!.setText(message)
            toast!!.duration = Toast.LENGTH_LONG
        }
        return this
    }

    /**
     * 显示Toast
     *
     * @return
     */
    fun show() {
        if (toast != null) {
            toast!!.show()
        }
    }

    companion object {
        private var sToastUtils: ToastUtils? = null

        val instance: ToastUtils
            get() {
                if (sToastUtils == null) {
                    synchronized(ToastUtils::class.java) {
                        if (sToastUtils == null) {
                            sToastUtils = ToastUtils()
                        }
                    }
                }
                return sToastUtils!!
            }
    }
}

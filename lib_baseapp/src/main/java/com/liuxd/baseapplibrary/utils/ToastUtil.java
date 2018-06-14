package com.liuxd.baseapplibrary.utils;

import android.widget.Toast;

import com.liuxd.baseapplibrary.App;

/**
 * Created by Liuxd on 2016/10/22 15:41.
 * <p>
 * 自定义Toast工具类
 * <p>
 * 支持取消之前的Toast，直接修改信息
 * </p>
 */

public class ToastUtil {
    private static ToastUtil sToastUtil;
    private Toast toast;

    private ToastUtil() {

    }

    public static ToastUtil getInstance() {
        if (sToastUtil == null) {
            synchronized (ToastUtil.class) {
                if (sToastUtil == null) {
                    sToastUtil = new ToastUtil();
                }
            }
        }
        return sToastUtil;
    }

    /**
     * 短时间显示Toast
     */
    public ToastUtil Short(CharSequence message) {
        if (toast == null) {
            toast = Toast.makeText(App.getInstance(), message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        return this;
    }

    /**
     * 长时间显示Toast
     */
    public ToastUtil Long(CharSequence message) {
        if (toast == null) {
            toast = Toast.makeText(App.getInstance(), message, Toast.LENGTH_LONG);
        } else {
            toast.setText(message);
            toast.setDuration(Toast.LENGTH_LONG);
        }
        return this;
    }

    /**
     * 显示Toast
     *
     * @return ToastUtil.this
     */
    public ToastUtil show() {
        toast.show();
        return this;
    }

    /**
     * 获取Toast
     *
     * @return
     */
    public Toast getToast() {
        return toast;
    }
}

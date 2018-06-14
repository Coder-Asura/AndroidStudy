package com.lxd.common_app.utils;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Liuxd on 2016/9/14 10:19.
 * 字体帮助类
 */
public class FontHelper {
    public static final String FONTS_DIR = "fonts/";
    public static final String DEFAULT_FONT = FONTS_DIR + "fontawesome-webfont.ttf";

    /**
     * 遍历rootView中所有TextView，并设置字体iconFont.ttf
     *
     * @param rootView 包含TextView的ViewGroup
     */
    public static void injectFont(View rootView) {
        injectFont(rootView, Typeface.createFromAsset(rootView.getContext().getAssets(), DEFAULT_FONT));
    }

    /**
     * 遍历rootView中所有TextView，并设置字体typeface
     *
     * @param rootView 包含TextView的ViewGroup
     * @param typeface 字体
     */
    public static void injectFont(View rootView, Typeface typeface) {
        if (rootView instanceof ViewGroup) {
            int count = ((ViewGroup) rootView).getChildCount();
            for (int i = 0; i < count; i++) {
                injectFont(((ViewGroup) rootView).getChildAt(i), typeface);
            }
        } else if (rootView instanceof TextView) {
            ((TextView) rootView).setTypeface(typeface);
        }
    }
}

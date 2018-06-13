package com.asura.greendao_study.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Arrays;

/**
 * 项目名：BLE App
 * 创建者：wuhj
 * 创建时间：2016/4/21 18:20
 * 描述：
 * 更新描述：
 */
public class UIUtils {

    /**
     * 判断TextView的输入是否为空
     *
     * @param textView
     * @return true/false
     */
    public static boolean isEmpty(TextView textView) {
        return TextUtils.isEmpty(textView.getText().toString().trim());
    }

    /**
     * 判断string是否为空
     *
     * @param string
     * @return true/false
     */
    public static boolean isEmpty(String string) {
        return TextUtils.isEmpty(string);
    }

    /**
     * 替代findviewById方法
     */
    public static <T extends View> T find(View view, int id) {
        return (T) view.findViewById(id);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param context （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param context （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * @param context
     * @return 屏幕的宽
     */
    public static int getWindowWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * @param context
     * @return 屏幕的高
     */
    public static int getWindowHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    public static String hsvToRgb(float hue, float saturation, float value) {

        int h = (int) (hue * 6);
        float f = hue * 6 - h;
        float p = value * (1 - saturation);
        float q = value * (1 - f * saturation);
        float t = value * (1 - (1 - f) * saturation);

        switch (h) {
            case 0:
                return rgbToString(value, t, p);
            case 1:
                return rgbToString(q, value, p);
            case 2:
                return rgbToString(p, value, t);
            case 3:
                return rgbToString(p, q, value);
            case 4:
                return rgbToString(t, p, value);
            case 5:
                return rgbToString(value, p, q);
            default:
                return null;
        }
    }

    public static float[] rgbToHsb(int rgbR, int rgbG, int rgbB) {
        int[] rgb = new int[]{rgbR, rgbG, rgbB};
        Arrays.sort(rgb);
        int max = rgb[2];
        int min = rgb[0];

        float hsbB = max / 255.0f;
        float hsbS = max == 0 ? 0 : (max - min) / (float) max;

        float hsbH = 0;
        if (max == rgbR && rgbG >= rgbB) {
            hsbH = (rgbG - rgbB) * 60f / (max - min) + 0;
        } else if (max == rgbR && rgbG < rgbB) {
            hsbH = (rgbG - rgbB) * 60f / (max - min) + 360;
        } else if (max == rgbG) {
            hsbH = (rgbB - rgbR) * 60f / (max - min) + 120;
        } else if (max == rgbB) {
            hsbH = (rgbR - rgbG) * 60f / (max - min) + 240;
        }

        return new float[]{hsbH, hsbS, hsbB};
    }

    public static String rgbToString(float r, float g, float b) {
        String rs = Integer.toHexString((int) (r * 255));
        if (rs.length() == 1) {
            rs = "0" + rs;
        }
        String gs = Integer.toHexString((int) (g * 255));
        if (gs.length() == 1) {
            gs = "0" + gs;
        }
        String bs = Integer.toHexString((int) (b * 255));
        if (bs.length() == 1) {
            bs = "0" + bs;
        }


        return rs + gs + bs;
    }

    public static String rgbToColorString(int r, int g, int b) {
        String rs = Integer.toHexString(r);
        if (rs.length() == 1) {
            rs = "0" + rs;
        }
        String gs = Integer.toHexString(g);
        if (gs.length() == 1) {
            gs = "0" + gs;
        }
        String bs = Integer.toHexString(b);
        if (bs.length() == 1) {
            bs = "0" + bs;
        }
        Log.d("colortest", r + "--" + rs);

        return rs + gs + bs;
    }

    /**
     * 根据亮度的变化修改当前的颜色()
     *
     * @param color      当前颜色
     * @param brightness 变化的亮度
     * @return color值
     */
    public static int brightnessToColor(String color, float brightness) {
        return brightnessToColor(Color.parseColor(color), brightness);
    }

    /**
     * 根据亮度的变化修改当前的颜色
     *
     * @param color      当前颜色
     * @param brightness 变化的亮度
     * @return color值
     */
    public static int brightnessToColor(int color, float brightness) {
        float[] hsbValues = colorToHsb(color);
        hsbValues[2] = brightness;
        return Color.HSVToColor(hsbValues);
    }

    /**
     * 颜色转换为hsb值
     *
     * @param color 颜色
     * @return hsb值数组
     */
    public static float[] colorToHsb(int color) {
        float[] hsbValues = new float[3];
        Color.colorToHSV(color, hsbValues);
        return hsbValues;
    }
}
package com.asura.android_study.view;

import android.content.Context;

import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author: Asuraliu
 * Date: 2021/11/1 15:09
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * Asuraliu 2021/11/1 1.0 首次创建
 */
public class NoScrollRecyclerView extends RecyclerView {
    public NoScrollRecyclerView(Context context) {
        super(context);
    }

    public NoScrollRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }
}

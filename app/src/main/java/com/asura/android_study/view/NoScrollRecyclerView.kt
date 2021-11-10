package com.asura.android_study.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import android.view.MotionEvent

/**
 * Author: Asuraliu
 * Date: 2021/11/1 15:09
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * Asuraliu 2021/11/1 1.0 首次创建
</desc></version></time></author> */
class NoScrollRecyclerView : RecyclerView {
    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context!!, attrs, defStyle) {}

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        return false
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        return false
    }
}
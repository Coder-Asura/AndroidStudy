package com.asura.android_study.view.slide

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import com.asura.android_study.R
import com.asura.android_study.utils.RectToPathUtil
import com.asura.android_study.utils.ifAllNotNull
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.SizeUtils

/**
 * Author: Asuraliu
 * Date: 2021/11/8 19:40
 * Description: 左转View
 * History:
 * <author> <time> <version> <desc>
 * Asuraliu 2021/11/8 1.0 首次创建
 */
class EmptyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var paint: Paint? = null
    private var viewRect: RectF? = null

    init {
        paint = Paint().apply {
            isAntiAlias = true
            style = Paint.Style.FILL
            color = Color.TRANSPARENT
        }
        viewRect = RectF()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = measureWidth(widthMeasureSpec)
        val height = measureHeight(heightMeasureSpec)
        //设置宽高
        setMeasuredDimension(width, height)
    }

    private fun measureWidth(measureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        var specSize = MeasureSpec.getSize(measureSpec)
        //wrap_content
        if (specMode == MeasureSpec.AT_MOST || specMode == MeasureSpec.UNSPECIFIED) {
            specSize = (ScreenUtils.getScreenWidth() - SizeUtils.dp2px(PADDING) * 2 - (SizeUtils.dp2px(MARGIN_END) * (SPAN_COUNT - 1))) / SPAN_COUNT
        }
        return specSize
    }

    private fun measureHeight(measureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        var specSize = MeasureSpec.getSize(measureSpec)
        //wrap_content
        if (specMode == MeasureSpec.AT_MOST || specMode == MeasureSpec.UNSPECIFIED) {
            specSize = (ScreenUtils.getScreenWidth() - SizeUtils.dp2px(PADDING) * 2 - (SizeUtils.dp2px(MARGIN_END) * (SPAN_COUNT - 1))) / SPAN_COUNT
        }
        return specSize
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewRect?.set(0.toFloat(), 0.toFloat(), w.toFloat(), h.toFloat())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        ifAllNotNull(canvas, paint, viewRect, allNotNullBlock = {
            canvas!!.drawRect(viewRect!!, paint!!)
        })
    }
}
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
 * Date: 2021/11/9 17:31
 * Description: 右转View
 * History:
 * <author> <time> <version> <desc>
 * Asuraliu 2021/11/8 1.0 首次创建
 */
class TurnRightView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var paint: Paint? = null
    private var viewRect: RectF? = null
    private var viewClipPath: Path? = null
    private var clipRect: RectF? = null
    private var clipPath: Path? = null
    private var bitmap: Bitmap? = null
    private var bitmapLeft: Float = 0f
    private var bitmapTop: Float = 0f

    var paintColor: Int = Color.BLUE
        set(value) {
            if (field != value) {
                field = value
                paint?.color = value
                invalidate()
            }
        }
    var viewChecked = false
        set(value) {
            field = value
            invalidate()
        }

    init {
        paint = Paint().apply {
            isAntiAlias = true
            style = Paint.Style.FILL
            color = paintColor
        }
        viewRect = RectF()
        clipRect = RectF()
        clipPath = Path()
        //添加图片
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.socket_ic_light_section_checked)
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
        viewRect?.let {
            viewClipPath =
                RectToPathUtil.rectRoundToPath(
                    it,
                    0f,
                    (h / 2).toFloat(),
                    (h / 2).toFloat(),
                    0f
                )
        }
        clipRect = RectF(
            0f,
            (h / 2 - SizeUtils.dp2px(MARGIN_BOTTOM) / 2).toFloat(),
            (w - h + h / 2 + SizeUtils.dp2px(MARGIN_BOTTOM) / 2).toFloat(),
            (h / 2 + SizeUtils.dp2px(MARGIN_BOTTOM) / 2).toFloat()
        )
        clipRect?.let {
            clipPath =
                RectToPathUtil.rectRoundToPath(
                    it,
                    0f,
                    (SizeUtils.dp2px(MARGIN_BOTTOM) / 2).toFloat(),
                    (SizeUtils.dp2px(MARGIN_BOTTOM) / 2).toFloat(),
                    0f
                )
        }
        bitmap?.let {
            bitmapLeft =
                (w - h + h / 2 + (SizeUtils.dp2px(MARGIN_BOTTOM) / 2) + (h / 2 - (SizeUtils.dp2px(MARGIN_BOTTOM) / 2) - it.width) / 2).toFloat()
            bitmapTop = (h / 2 - it.height / 2).toFloat()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        ifAllNotNull(canvas, paint, viewClipPath, clipPath, bitmap, allNotNullBlock = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                canvas!!.clipOutPath(clipPath!!)
            } else {
                canvas!!.clipPath(clipPath!!, Region.Op.DIFFERENCE)
            }
            canvas.drawPath(viewClipPath!!, paint!!)
            if (viewChecked) {
                canvas.drawBitmap(bitmap!!, bitmapLeft, bitmapTop, paint)
            }
        })
    }
}
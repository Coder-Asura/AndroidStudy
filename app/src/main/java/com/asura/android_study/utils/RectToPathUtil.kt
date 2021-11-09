package com.asura.android_study.utils

import android.graphics.Path
import android.graphics.RectF
import com.blankj.utilcode.util.ConvertUtils

/**
 * Author: Arze
 * Date: 2021/8/10 17:22
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * Arze 2021/8/10 1.0
 */
object RectToPathUtil {

    fun rectRoundToPath(rectF: RectF, radius: Float): Path {
        val path = Path()
        val left = rectF.left
        val top = rectF.top
        val right = rectF.right
        val bottom = rectF.bottom

        val value = ConvertUtils.dp2px(radius)

        path.moveTo(left, top + value)

        val leftTop = RectF(left, top, left + 2 * value, top + 2 * value)
        path.arcTo(leftTop, 180f, 90f)

        path.lineTo(right - ConvertUtils.dp2px(24f), top)

        val rightTop = RectF(right - 2 * value, top, right, top + 2 * value)
        path.arcTo(rightTop, 270f, 90f)


        path.lineTo(right, bottom - ConvertUtils.dp2px(24f))
        val rightBottom = RectF(right - 2 * value, bottom - 2 * value, right, bottom)
        path.arcTo(rightBottom, 0f, 90f)

        path.lineTo(left + ConvertUtils.dp2px(24f), bottom)

        val leftBottom = RectF(left, bottom - 2 * value, left + 2 * value, bottom)
        path.arcTo(leftBottom, 90f, 90f)

        path.lineTo(left, top + ConvertUtils.dp2px(24f))
        return path
    }

    fun rectRoundToPath(rectF: RectF, topLeftRadius: Float, topRightRadius: Float, bottomRightRadius: Float, bottomLeftRadius: Float): Path {
        val path = Path()
        path.addRoundRect(
            rectF,
            floatArrayOf(
                topLeftRadius,
                topLeftRadius,
                topRightRadius,
                topRightRadius,
                bottomRightRadius,
                bottomRightRadius,
                bottomLeftRadius,
                bottomLeftRadius
            ),
            Path.Direction.CW
        )
        return path
    }

}
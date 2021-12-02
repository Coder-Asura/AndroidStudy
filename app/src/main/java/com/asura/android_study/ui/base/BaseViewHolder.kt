package com.asura.android_study.ui.base

import androidx.recyclerview.widget.RecyclerView
import android.util.SparseArray
import com.asura.android_study.ui.base.BaseViewHolder
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.asura.android_study.utils.ImageLoadUtil
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.os.Build
import android.view.animation.AlphaAnimation
import android.text.util.Linkify
import android.graphics.Typeface
import android.view.View
import android.view.View.OnTouchListener
import android.view.View.OnLongClickListener
import android.widget.*

/**
 * Created by Liuxd on 2016/11/8 14:48.
 *
 *
 * RecyclerView 通用的ViewHolder
 *
 * 集合了常用的设置控件属性方法
 */
class BaseViewHolder(private val mContext: Context, private val mItemView: View) : RecyclerView.ViewHolder(
    mItemView
) {
    private val mViews: SparseArray<View?> = SparseArray()

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    fun <T : View?> getView(viewId: Int): T? {
        var view = mViews[viewId]
        if (view == null) {
            view = mItemView.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view as T?
    }

    fun setText(viewId: Int, text: String?): BaseViewHolder {
        val tv = getView<TextView>(viewId)!!
        tv.text = text
        return this
    }

    fun setImageResource(viewId: Int, resId: Int): BaseViewHolder {
        val view = getView<ImageView>(viewId)!!
        view.setImageResource(resId)
        return this
    }

    fun setImageBitmap(viewId: Int, bitmap: Bitmap?): BaseViewHolder {
        val view = getView<ImageView>(viewId)!!
        view.setImageBitmap(bitmap)
        return this
    }

    fun setImageDrawable(viewId: Int, drawable: Drawable?): BaseViewHolder {
        val view = getView<ImageView>(viewId)!!
        view.setImageDrawable(drawable)
        return this
    }

//    fun setImageSrc(viewId: Int, url: String?): BaseViewHolder {
//        val view = getView<ImageView>(viewId)!!
//        ImageLoadUtil.getInstance().load(mContext, url, view)
//        return this
//    }
//
//    fun setImageSrc(viewId: Int, resId: Int): BaseViewHolder {
//        val view = getView<ImageView>(viewId)!!
//        ImageLoadUtil.getInstance().load(mContext, resId, view)
//        return this
//    }

    fun setBackgroundColor(viewId: Int, color: Int): BaseViewHolder {
        val view = getView<View>(viewId)!!
        view.setBackgroundColor(color)
        return this
    }

    fun setBackgroundRes(viewId: Int, backgroundRes: Int): BaseViewHolder {
        val view = getView<View>(viewId)!!
        view.setBackgroundResource(backgroundRes)
        return this
    }

    fun setTextColor(viewId: Int, textColor: Int): BaseViewHolder {
        val view = getView<TextView>(viewId)!!
        view.setTextColor(textColor)
        return this
    }

    @SuppressLint("NewApi")
    fun setAlpha(viewId: Int, value: Float): BaseViewHolder {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView<View>(viewId)!!.alpha = value
        } else {
            // Pre-honeycomb hack to set Alpha value
            val alpha = AlphaAnimation(value, value)
            alpha.duration = 0
            alpha.fillAfter = true
            getView<View>(viewId)!!.startAnimation(alpha)
        }
        return this
    }

    fun setVisible(viewId: Int, visible: Boolean): BaseViewHolder {
        val view = getView<View>(viewId)!!
        view.visibility = if (visible) View.VISIBLE else View.GONE
        return this
    }

    fun linkify(viewId: Int): BaseViewHolder {
        val view = getView<TextView>(viewId)!!
        Linkify.addLinks(view, Linkify.ALL)
        return this
    }

    fun setTypeface(typeface: Typeface?, vararg viewIds: Int): BaseViewHolder {
        for (viewId in viewIds) {
            val view = getView<TextView>(viewId)!!
            view.typeface = typeface
            view.paintFlags = view.paintFlags or Paint.SUBPIXEL_TEXT_FLAG
        }
        return this
    }

    fun setProgress(viewId: Int, progress: Int): BaseViewHolder {
        val view = getView<ProgressBar>(viewId)!!
        view.progress = progress
        return this
    }

    fun setRating(viewId: Int, rating: Float): BaseViewHolder {
        val view = getView<RatingBar>(viewId)!!
        view.rating = rating
        return this
    }

    fun setTag(viewId: Int, tag: Any?): BaseViewHolder {
        val view = getView<View>(viewId)!!
        view.tag = tag
        return this
    }

    fun setTag(viewId: Int, key: Int, tag: Any?): BaseViewHolder {
        val view = getView<View>(viewId)!!
        view.setTag(key, tag)
        return this
    }

    fun setChecked(viewId: Int, checked: Boolean): BaseViewHolder {
        val view = getView<View>(viewId) as Checkable
        view.isChecked = checked
        return this
    }

    /**
     * 关于事件的
     */
    fun setOnClickListener(
        viewId: Int,
        listener: View.OnClickListener?
    ): BaseViewHolder {
        val view = getView<View>(viewId)!!
        view.setOnClickListener(listener)
        return this
    }

    fun setOnTouchListener(
        viewId: Int,
        listener: OnTouchListener?
    ): BaseViewHolder {
        val view = getView<View>(viewId)!!
        view.setOnTouchListener(listener)
        return this
    }

    fun setOnLongClickListener(
        viewId: Int,
        listener: OnLongClickListener?
    ): BaseViewHolder {
        val view = getView<View>(viewId)!!
        view.setOnLongClickListener(listener)
        return this
    }

}
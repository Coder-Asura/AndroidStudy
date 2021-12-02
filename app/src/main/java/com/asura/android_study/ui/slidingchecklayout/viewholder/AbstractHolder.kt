package com.asura.android_study.ui.slidingchecklayout.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Author: Asuraliu
 * Date: 2021/11/2 16:42
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * Asuraliu 2021/11/2 1.0 首次创建
 */
abstract class AbstractHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(model: T)
}

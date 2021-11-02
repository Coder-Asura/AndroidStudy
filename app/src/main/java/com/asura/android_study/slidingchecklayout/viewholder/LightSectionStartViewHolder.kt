package com.asura.android_study.slidingchecklayout.viewholder

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.asura.android_study.R
import com.asura.android_study.model.LightSection

/**
 * Author: Asuraliu
 * Date: 2021/11/2 16:43
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * Asuraliu 2021/11/2 1.0 首次创建
 */
class LightSectionStartViewHolder(itemView: View) : AbstractHolder<LightSection>(itemView) {
    var mNameTv: TextView? = null

    init {
        mNameTv = itemView.findViewById(R.id.item_name_tv)
    }

    override fun onBind(model: LightSection) {
        mNameTv?.text = model.shape.name.substring("SHAPE_".length)
        if (model.check) {
            itemView.setBackgroundResource(R.color.colorAccent)
            mNameTv?.setTextColor(ContextCompat.getColor(itemView.context, android.R.color.holo_orange_light))
        } else {
            itemView.setBackgroundResource(R.color.colorPrimaryDark)
            mNameTv?.setTextColor(ContextCompat.getColor(itemView.context, android.R.color.darker_gray))
        }
    }

}
package com.asura.android_study.slidingchecklayout

import com.asura.android_study.model.LightSection
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.asura.android_study.model.SectionShape
import android.view.LayoutInflater
import com.asura.android_study.R
import com.asura.android_study.slidingchecklayout.viewholder.AbstractHolder
import com.asura.android_study.slidingchecklayout.viewholder.LightSectionStartViewHolder
import java.util.ArrayList

class LightSectionAdapter(dataList: MutableList<LightSection>) : RecyclerView.Adapter<AbstractHolder<LightSection?>>() {
    private var mDataList: MutableList<LightSection> = ArrayList()
    fun getEntityByPosition(position: Int): LightSection {
        return mDataList[position]
    }

    fun getEntityIndexByPosition(position: Int): Int {
        return mDataList[position].index
    }

    fun setmDataList(mDataList: List<LightSection>?) {
        if (mDataList != null) {
            this.mDataList.clear()
            this.mDataList.addAll(mDataList)
            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mDataList[position].shape.ordinal
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    init {
        mDataList = dataList
    }

    override fun onBindViewHolder(holder: AbstractHolder<LightSection?>, position: Int) {
        val lightSection = mDataList[holder.adapterPosition]
        holder.onBind(lightSection)
        holder.itemView.setOnClickListener {
            lightSection.check = !lightSection.check
            notifyItemChanged(holder.adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractHolder<LightSection?> {
        return if (viewType == SectionShape.SHAPE_TURNING_LEFT.ordinal
            || viewType == SectionShape.SHAPE_TURNING_RIGHT.ordinal
        ) {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_main_rv2, parent, false)
            LightSectionStartViewHolder(itemView) as AbstractHolder<LightSection?>
        } else {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_main_rv, parent, false)
            LightSectionStartViewHolder(itemView) as AbstractHolder<LightSection?>
        }
    }
}
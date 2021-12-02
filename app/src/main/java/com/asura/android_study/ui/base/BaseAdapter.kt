package com.asura.android_study.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Liuxd on 2016/11/8 14:55.
 *
 *
 * RecyclerView 通用的适配器
 *
 * 通过convert（）抽象方法将holder及数据公布出去，以及基本的点击、长按事件监听处理
 * @param datas    数据源
 * @param layoutId 布局id
 */
abstract class BaseAdapter<T> constructor(
    private var mDatas: MutableList<T>?,
    private val mLayoutId: Int
) : RecyclerView.Adapter<BaseViewHolder>() {
    private var mOnItemClickListener: OnItemClickListener? = null
    private var mOnItemLongClickListener: OnItemLongClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(mLayoutId, parent, false)
        return BaseViewHolder(parent.context, itemView)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        convert(holder, mDatas!![position])
        //设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener { v -> mOnItemClickListener!!.onItemClickListener(v, holder.adapterPosition) }
        }
        //设置长按事件
        if (mOnItemLongClickListener == null) {
            holder.itemView.setOnLongClickListener { v -> mOnItemLongClickListener!!.onItemLongClickListener(v, holder.adapterPosition) }
        }
    }

    override fun getItemCount(): Int {
        return if (mDatas != null) mDatas!!.size else 0
    }

    fun setDatas(datas: MutableList<T>?, loadMore: Boolean) {
        if (loadMore) {
            mDatas!!.addAll(datas!!)
        } else {
            mDatas = datas
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        mOnItemClickListener = onItemClickListener
    }

    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener?) {
        mOnItemLongClickListener = onItemLongClickListener
    }

    interface OnItemClickListener {
        fun onItemClickListener(v: View?, position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClickListener(v: View?, position: Int): Boolean
    }

    abstract fun convert(holder: BaseViewHolder, data: T)
}
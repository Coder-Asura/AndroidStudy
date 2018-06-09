package com.asura.jsoup_demo.ui.main.new

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.asura.jsoup_demo.R
import com.asura.jsoup_demo.bean.NewGirl
import com.asura.jsoup_demo.util.ImageUtils

/**
 * 最新美女适配器
 *
 * Created by Liuxd on 2018/6/9 16:01.
 */
class NewGirlAdapter() : RecyclerView.Adapter<NewGirlAdapter.NewGirlViewHolder>() {
    lateinit var context: Context
    lateinit var data: List<NewGirl>
    lateinit var callback: Callback

    constructor(context: Context, data: List<NewGirl>, callback: Callback) : this() {
        this.context = context
        this.data = data
        this.callback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewGirlViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_new_girl, parent, false)
        return NewGirlViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (data == null || data.size == 0) 0 else data.size
    }

    override fun onBindViewHolder(holder: NewGirlViewHolder, position: Int) {
        holder.tv_girl_title?.text = data.get(position).title
        ImageUtils.loadImage(context, data.get(position).imgUrl, holder.iv_new_girl)
        holder.itemView.setOnClickListener {
            callback.onItemClick(position)
        }
    }


    inner class NewGirlViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tv_girl_title = itemView?.findViewById<TextView>(R.id.tv_girl_title)
        var iv_new_girl = itemView?.findViewById<ImageView>(R.id.iv_new_girl)
    }

    interface Callback {
        fun onItemClick(position: Int)
    }
}
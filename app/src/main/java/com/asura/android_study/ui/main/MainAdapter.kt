package com.asura.android_study.ui.main

import android.content.Context
import android.content.Intent
import com.asura.android_study.R
import com.asura.android_study.ui.base.BaseActivity
import com.asura.android_study.ui.base.BaseAdapter
import com.asura.android_study.ui.base.BaseViewHolder

/**
 * Author: Asuraliu
 * Date: 2021/12/2 10:33
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * Asuraliu 2021/12/2 1.0 首次创建
 */
class MainAdapter(private val context: Context, mDatas: MutableList<MainItem>?) : BaseAdapter<MainItem>(mDatas, R.layout.layout_item_main) {
    override fun convert(holder: BaseViewHolder, data: MainItem) {
        holder.setText(R.id.item_btn_main, data.title)
        holder.setOnClickListener(R.id.item_btn_main) {
            if (data.intentClass != null) {
                context.startActivity(Intent(context, data.intentClass).putExtra(BaseActivity.KEY_TITLE, data.title))
            } else if (data.block != null) {
                data.block.invoke()
            }
        }
    }
}
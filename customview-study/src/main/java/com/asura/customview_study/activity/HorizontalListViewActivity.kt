package com.asura.customview_study.activity

import com.asura.customview_study.R
import com.asura.customview_study.adapter.ScrollerAdapter
import com.asura.customview_study.base.BaseActivity
import kotlinx.android.synthetic.main.activity_horizontal_list_view.*
import java.util.*

/**
 * @author Created by Asura on 2018/6/14 10:20.
 */
class HorizontalListViewActivity : BaseActivity() {
    private var mScrollerAdapter: ScrollerAdapter? = null

    override fun setLayoutId(): Int = R.layout.activity_horizontal_list_view

    override fun initView() {
        val strings = ArrayList<String>()
        for (i in 0..39) {
            strings.add("新开关$i")
        }
        mScrollerAdapter = ScrollerAdapter(this@HorizontalListViewActivity, strings)
        hlscrol.setAdapter(mScrollerAdapter)
    }

}
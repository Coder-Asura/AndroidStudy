package com.asura.android_study.ui.horizontallistview

import com.asura.android_study.R
import com.asura.android_study.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_horizontal_listview.*
import java.util.*

/**
 * Author: Asuraliu
 * Date: 2021/12/2 10:51
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * Asuraliu 2021/12/2 1.0 首次创建
 */
class HorizontalListViewActivity : BaseActivity() {

    private var mScrollerAdapter: ScrollerAdapter? = null

    override fun init() {
        val strings: MutableList<String> = ArrayList()
        for (i in 0..39) {
            strings.add("新开关$i")
        }
        mScrollerAdapter = ScrollerAdapter(this, strings)
        hlscrol.adapter = mScrollerAdapter

        btn_scroll.setOnClickListener {
            hlscrol.scrollTo(600)
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_horizontal_listview
    }
}
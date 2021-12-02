package com.asura.android_study.ui.constranitlayout

import android.graphics.Color
import com.asura.android_study.R
import com.asura.android_study.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_constraint_layout.*

/**
 * 约束布局
 *
 * @author Created by Asura on 2018/6/13 13:35.
 */
class ConstraintLayoutActivity : BaseActivity() {

    override fun setLayoutId(): Int {
        return R.layout.activity_constraint_layout
    }

    override fun initView() {
        btn1.text = "dsadasd"
        btn1.setTextColor(Color.parseColor("#ff0000"))
    }
}
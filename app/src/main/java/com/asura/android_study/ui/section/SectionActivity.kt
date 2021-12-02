package com.asura.android_study.ui.section

import android.graphics.Color
import com.asura.android_study.R
import com.asura.android_study.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_section.*

/**
 * Author: Asuraliu
 * Date: 2021/12/2 11:27
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * Asuraliu 2021/12/2 1.0 首次创建
 */
class SectionActivity : BaseActivity() {
    override fun initView() {
        btn_change_color.setOnClickListener {
            ddddd.paintColor = Color.BLACK
            ddddd.viewChecked = true
            ddddd2.paintColor = Color.BLACK
            ddddd2.viewChecked = true
            ddddd3.paintColor = Color.BLACK
            ddddd3.viewChecked = true
            ddddd4.paintColor = Color.BLACK
            ddddd4.viewChecked = true
            ddddd5.paintColor = Color.BLACK
            ddddd5.viewChecked = true
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_section
    }
}
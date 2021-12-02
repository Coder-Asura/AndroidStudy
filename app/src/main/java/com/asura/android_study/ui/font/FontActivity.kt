package com.asura.android_study.ui.font

import com.asura.android_study.R
import com.asura.android_study.ui.base.BaseActivity
import com.asura.android_study.utils.FontHelper
import kotlinx.android.synthetic.main.activity_font.*

/**
 * Author: Asuraliu
 * Date: 2021/12/2 10:51
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * Asuraliu 2021/12/2 1.0 首次创建
 */
class FontActivity : BaseActivity() {
    override fun initView() {
        FontHelper.injectFont(csl_font)
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_font
    }
}
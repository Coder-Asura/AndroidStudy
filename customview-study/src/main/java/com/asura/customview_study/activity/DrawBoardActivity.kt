package com.asura.customview_study.activity

import com.asura.customview_study.R
import com.asura.customview_study.base.BaseActivity
import kotlinx.android.synthetic.main.activity_draw_board.*

/**
 * 画板页面
 *
 * @author Created by Asura on 2018/6/13 13:35.
 */
class DrawBoardActivity : BaseActivity() {
    override fun setLayoutId(): Int = R.layout.activity_draw_board

    override fun initView() {
        btn_clearView.setOnClickListener {
            drawingBoard.clearView()
        }
    }
}
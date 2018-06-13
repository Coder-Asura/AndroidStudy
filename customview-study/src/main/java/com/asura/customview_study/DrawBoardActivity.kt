package com.asura.customview_study

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_draw_board.*

/**
 * 画板页面
 *
 * @author Created by Asura on 2018/6/13 13:35.
 */
class DrawBoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_board)
        btn_clearView.setOnClickListener {
            drawingBoard.clearView()
        }
    }
}
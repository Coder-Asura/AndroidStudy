package com.asura.customview_study.activity

import android.content.Intent
import android.view.View
import com.asura.customview_study.R
import com.asura.customview_study.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), View.OnClickListener {
    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        btn_drawBoard.setOnClickListener(this)
        btn_leafAnim.setOnClickListener(this)
        btn_canvasView.setOnClickListener(this)
        btn_asuraView.setOnClickListener(this)
        btn_horizontalListView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent: Intent = Intent()
        when (v) {
            btn_drawBoard -> {
                intent.setClass(this@MainActivity, DrawBoardActivity::class.java)
            }
            btn_leafAnim -> {
                intent.setClass(this@MainActivity, LeafLoadingActivity::class.java)
            }
            btn_canvasView -> {
                intent.setClass(this@MainActivity, CanvasViewActivity::class.java)
            }
            btn_asuraView -> {
                intent.setClass(this@MainActivity, AsuraViewActivity::class.java)
            }
            btn_horizontalListView -> {
                intent.setClass(this@MainActivity, HorizontalListViewActivity::class.java)
            }
        }
        startActivity(intent)
    }

}

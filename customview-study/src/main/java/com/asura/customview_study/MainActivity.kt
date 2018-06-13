package com.asura.customview_study

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        var intent: Intent = Intent()
        when (v) {
            btn_drawBoard -> {
                intent.setClass(this@MainActivity, DrawBoardActivity::class.java)
            }
            btn_constraintLayout -> {
                intent.setClass(this@MainActivity, ConstraintLayoutActivity::class.java)
            }
            btn_constraint_transition -> {
                intent.setClass(this@MainActivity, ConstraintLayoutTransitionActivity::class.java)
            }
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_drawBoard.setOnClickListener(this)
        btn_constraintLayout.setOnClickListener(this)
        btn_constraint_transition.setOnClickListener(this)
    }
}

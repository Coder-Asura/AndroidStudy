package com.asura.android_study.ui.constranitlayout

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asura.android_study.R
import kotlinx.android.synthetic.main.activity_constraint_layout.*

/**
 * 约束布局
 *
 * @author Created by Asura on 2018/6/13 13:35.
 */
class ConstraintLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout)
        btn1.text="dsadasd"
        btn1.setTextColor(Color.parseColor("#ff0000"))
    }
}
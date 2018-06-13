package com.asura.customview_study

import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import kotlinx.android.synthetic.main.activity_constraint_layout_transition_a.*

/**
 * 画板页面
 *
 * @author Created by Asura on 2018/6/13 13:35.
 */
class ConstraintLayoutTransitionActivity : AppCompatActivity() {
    var isFirst = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout_transition_a)
        val first = ConstraintSet()
        val second = ConstraintSet()
        first.clone(contentPanel)
        second.clone(this, R.layout.activity_constraint_layout_transition_b)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            start.setOnClickListener {
                if (isFirst) {
                    isFirst = false;
                    TransitionManager.beginDelayedTransition(contentPanel)
                    second.applyTo(contentPanel)
                } else {
                    isFirst = true
                    TransitionManager.beginDelayedTransition(contentPanel)
                    first.applyTo(contentPanel)
                }
            }
        }
    }

}
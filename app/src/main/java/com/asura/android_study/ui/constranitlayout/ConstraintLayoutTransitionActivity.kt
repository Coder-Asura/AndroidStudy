package com.asura.android_study.ui.constranitlayout

import android.os.Build
import android.os.Bundle
import android.transition.TransitionManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.asura.android_study.R
import com.asura.android_study.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_constraint_layout_transition_a.*

/**
 * 约束布局转场动画
 *
 * @author Created by Asura on 2018/6/13 13:35.
 */
class ConstraintLayoutTransitionActivity : BaseActivity() {
    var isFirst = true

    override fun setLayoutId(): Int {
        return R.layout.activity_constraint_layout_transition_a
    }

    override fun initView() {
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
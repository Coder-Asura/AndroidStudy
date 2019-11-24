package com.asura.android_study.activity.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * Created by Liuxd on 2016/11/11 15:06.
 */

public class DrawerBehavior extends CoordinatorLayout.Behavior<TextView> {
    private int mStartY;

    public DrawerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        //记录开始的Y坐标  也就是toolbar起始Y坐标
        if (mStartY==0){
            mStartY = (int) dependency.getY();
        }

        //计算toolbar从开始移动到最后的百分比
        float percent = dependency.getY()/mStartY;
        //改变child的坐标(从消失，到可见)
        child.setY(child.getHeight()*(1-percent) - child.getHeight());
        child.startAnimation(new AlphaAnimation(1,(1-percent)));

        return true;

    }
}

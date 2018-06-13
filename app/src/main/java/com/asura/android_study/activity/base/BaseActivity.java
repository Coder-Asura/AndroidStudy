package com.asura.android_study.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.asura.android_study.R;
import com.asura.android_study.activity.MainActivity;
import com.jaeger.library.StatusBarUtil;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrPosition;

import butterknife.BindColor;
import butterknife.ButterKnife;

/**
 * Created by Liuxd on 2016/10/19 10:24.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity {
    @BindColor(R.color.statusBarColor)
    int statusBarColor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        initView();
        SlidrConfig config = new SlidrConfig.Builder()
//                .primaryColor(getResources().getColor(R.color.primary)//滑动时状态栏的渐变结束的颜色
//                        .secondaryColor(getResources().getColor(R.color.secondary)//滑动时状态栏的渐变开始的颜色
                .position(SlidrPosition.LEFT)//从左边滑动
//                                .sensitivity(1f)
//                                .scrimColor(Color.BLACK)
//                                .scrimStartAlpha(0.8f)//滑动开始时两个Activity之间的透明度
//                                .scrimEndAlpha(0f)//滑动结束时两个Activity之间的透明度
                                .velocityThreshold(4800)//超过这个滑动速度，忽略位移限定值就切换Activity
                .distanceThreshold(0.5f)//滑动位移占屏幕的百分比，超过这个间距就切换Activity
                .edge(true)//只能从边上开始滑动
//                .edgeSize(0.3f) // The % of the screen that counts as the edge, default 18%
//                                .listener(new SlidrListener(){...})
                .build();
        if (BaseActivity.this instanceof MainActivity) {
            Slidr.attach(this, config).lock();
        } else {
            Slidr.attach(this, config);
        }
//        Slidr.attach(this);
        setStatusBar();
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, statusBarColor, 0);
    }
}

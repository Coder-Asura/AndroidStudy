package com.lxd.common_app.base;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.jaeger.library.StatusBarUtil;
import com.lxd.common_app.R;
import com.lxd.common_app.utils.AppManager;
import com.lxd.common_app.utils.LogUtil;
import com.lxd.common_app.utils.ToastUtil;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrPosition;

import butterknife.BindColor;
import butterknife.ButterKnife;

/**
 * Created by Liuxd on 2016/11/8 15:35.
 * <p>
 * 通用的Activity
 * </p>
 * 集合了ButterKnife、沉浸式状态栏、侧滑返回、activity栈管理以及一些常用的方法
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity {
    @BindColor(R.color.statusBarColor)
    int statusBarColor;

    public final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(TAG, "onCreate");
        setContentView(setLayoutId());//设置布局文件
        ButterKnife.bind(this);//奶油刀绑定布局
        setStatusBarColor(statusBarColor, 0);//设置状态栏颜色
        initSlidrConfig();//初始化滑动返回
        AppManager.getInstance().addActivity(this);
        initToolBar();
        init();//一些初始化操作
    }

    private void initToolBar() {
    }

    private void initSlidrConfig() {
        SlidrConfig config = new SlidrConfig.Builder()
//                .primaryColor(getResources().getColor(R.color.colorPrimary))//滑动时状态栏的渐变结束的颜色
                .position(SlidrPosition.LEFT)//从左边滑动
//                .sensitivity(1f)
//                .scrimColor(Color.BLACK)
//                .scrimStartAlpha(0.8f)//滑动开始时两个Activity之间的透明度
//                .scrimEndAlpha(0f)//滑动结束时两个Activity之间的透明度
                .velocityThreshold(4800)//超过这个滑动速度，忽略位移限定值就切换Activity
                .distanceThreshold(0.5f)//滑动位移占屏幕的百分比，超过这个间距就切换Activity
                .edge(true)//只能从边上开始滑动
//                .edgeSize(0.3f) // The % of the screen that counts as the edge, default 18%
//              .listener(new SlidrListener(){...})
                .build();
        Slidr.attach(this, config);
    }

    public void setStatusBarColor(int color, int alpha) {
        StatusBarUtil.setColor(this, color, alpha);
    }

    public void setSwipeBackSupport(boolean support) {
        if (support) {
            Slidr.attach(this).unlock();
        } else {
            Slidr.attach(this).lock();
        }
    }

    public void showToast(int msgId) {
        ToastUtil.getInstance().Short(getString(msgId)).show();
    }

    public void showToast(String msg) {
        ToastUtil.getInstance().Short(msg).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 跳转时隐藏键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (imm != null) {
            if (view != null && view instanceof EditText) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        }
        LogUtil.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
        LogUtil.d(TAG, "onDestroy");
    }
}

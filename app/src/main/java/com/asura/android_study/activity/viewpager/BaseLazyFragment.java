package com.asura.android_study.activity.viewpager;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.asura.a_log.ALog;

import butterknife.ButterKnife;

/**
 * Created by Liuxd on 2016/11/12 15:39.
 */

public abstract class BaseLazyFragment extends Fragment {
    public final String TAG = this.getClass().getSimpleName();
    protected View mRootView;
    protected Context mContext;
    protected boolean isVisible;
    private boolean isPrepared;
    private boolean isFirst = true;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != mRootView) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (null != parent) {
                parent.removeView(mRootView);
            }
        }
        mRootView = inflater.inflate(setLayoutId(), null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }
//--------------------system method callback------------------------//

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        initPrepare();
        ALog.INSTANCE.d(TAG + "准备数据");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
//            onInvisible();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            setUserVisibleHint(true);
        }
    }
    //--------------------------------method---------------------------//

    /**
     * 懒加载
     */
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirst) {
            return;
        }
        initData();
        ALog.INSTANCE.d(TAG + " 正式初始化");
        isFirst = false;
    }

    //--------------------------abstract method------------------------//

    //    /**
//     * 在onActivityCreated中调用的方法，可以用来进行初始化操作。
//     */
    protected abstract void initPrepare();

    /**
     * fragment被设置为不可见时调用
     */
//    protected abstract void onInvisible();

    /**
     * 这里获取数据，刷新界面
     */
    protected abstract void initData();

    /**
     * 初始化布局，请不要把耗时操作放在这个方法里，这个方法用来提供一个
     * 基本的布局而非一个完整的布局，以免ViewPager预加载消耗大量的资源。
     */
    public abstract int setLayoutId();
}

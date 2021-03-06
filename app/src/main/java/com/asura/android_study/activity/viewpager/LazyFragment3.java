package com.asura.android_study.activity.viewpager;

import android.os.Bundle;
import android.widget.TextView;

import com.asura.android_study.R;

import butterknife.BindView;

/**
 * Created by Liuxd on 2016/11/12 15:50.
 */

public class LazyFragment3 extends BaseLazyFragment {

    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.tv_name)
    TextView tv_name;

    public LazyFragment3() {
    }

    public static LazyFragment3 create(String text) {
        LazyFragment3 fragment = new LazyFragment3();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initPrepare() {
        mTv.setText("333333");
        tv_name.setText(getArguments().getString("text"));
    }

    @Override
    protected void initData() {
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_lazy1;
    }

    public void setTv_name(String name) {
        tv_name.setText(name);
    }
}

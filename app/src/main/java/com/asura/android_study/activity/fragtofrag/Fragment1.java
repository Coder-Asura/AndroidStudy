package com.asura.android_study.activity.fragtofrag;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.asura.android_study.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Liuxd on 2016/11/1 21:05.
 */

public class Fragment1 extends Fragment {
    private MyListener listener;

    @BindView(R.id.button1)
    Button mButton1;
    @BindView(R.id.textView1)
    TextView mTextView1;

    public Fragment1() {

    }

    public static Fragment1 createInstance() {
        return new Fragment1();
    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    protected void onAttachToContext(Context context) {
        listener = (MyListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment1, null);
        ButterKnife.bind(this, root);
        return root;
    }


    @OnClick(R.id.button1)
    public void onClick() {
        listener.sendFrom1("收到fragment1的值");
    }

    public void setTextView1(String info) {
        mTextView1.setText(info);
    }
}

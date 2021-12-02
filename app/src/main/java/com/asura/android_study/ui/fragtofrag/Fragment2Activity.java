package com.asura.android_study.ui.fragtofrag;


import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.asura.android_study.R;
import com.asura.android_study.ui.base.BaseActivity;

/**
 * Created by Liuxd on 2016/11/1 21:04.
 */
public class Fragment2Activity extends BaseActivity implements MyListener {

    private Fragment1 mFragment1;
    private Fragment2 mFragment2;
    private Fragment3 mFragment3;
    private Fragment4 mFragment4;

    @Override
    public void sendFrom1(String info) {
        Toast.makeText(this, "Activity成功接收1:" + info, Toast.LENGTH_SHORT).show();
        change(2, info);
    }

    @Override
    public void sendFrom2(String info) {
        Toast.makeText(this, "Activity成功接收2:" + info, Toast.LENGTH_SHORT).show();
        mFragment1.setTextView1(info);
    }

    private void change(int id, String info) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideAll(transaction);
        switch (id) {
            case 2:
                if (mFragment2 == null) {
                    mFragment2 = Fragment2.createInstance(info);
                    transaction.add(R.id.content2, mFragment2, "fragment2");
                } else {
                    transaction.show(mFragment2);
                }
                break;
            case 3:
                if (mFragment3 == null) {
                    mFragment3 = Fragment3.createInstance(info);
                    transaction.add(R.id.content2, mFragment3, "fragment3");
                } else {
                    transaction.show(mFragment3);
                }
                break;
            case 4:
                if (mFragment4 == null) {
                    mFragment4 = Fragment4.createInstance(info);
                    transaction.add(R.id.content2, mFragment4, "fragment4");
                } else {
                    transaction.show(mFragment4);
                }
                break;
            default:
        }
        transaction.commit();
    }

    private void hideAll(FragmentTransaction transaction) {
        if (mFragment2 != null && !mFragment2.isHidden()) {
            transaction.hide(mFragment2);
        }
        if (mFragment3 != null && !mFragment3.isHidden()) {
            transaction.hide(mFragment3);
        }
        if (mFragment4 != null && !mFragment4.isHidden()) {
            transaction.hide(mFragment4);
        }
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_my;
    }

    @Override
    public void initView() {
        mFragment1 = Fragment1.createInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content1, mFragment1, "fragment1");
        transaction.commit();
        RadioGroup mRadioGroup = findViewById(R.id.radioGroup);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio1:
                        change(2, "选择了2");
                        break;
                    case R.id.radio2:
                        change(3, "选择了3");
                        break;
                    case R.id.radio3:
                        change(4, "选择了4");
                        break;
                    default:
                        break;
                }
            }
        });
        ((RadioButton) mRadioGroup.getChildAt(0)).setChecked(true);
    }
}

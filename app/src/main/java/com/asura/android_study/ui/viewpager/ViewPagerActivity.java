package com.asura.android_study.ui.viewpager;

import androidx.viewpager.widget.ViewPager;

import com.asura.a_log.ALog;
import com.asura.android_study.R;
import com.asura.android_study.ui.base.BaseActivity;
import com.asura.android_study.ui.base.BaseLazyFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liuxd on 2016/11/12 14:44.
 */

public class ViewPagerActivity extends BaseActivity {
    private ViewPager mViewpager;
    List<BaseLazyFragment> mFragmentList;
    private List<String> mTitles;

    private TabLayout mTabLayout;

    private LazyFragment1 mLazyFragment1;
    private LazyFragment2 mLazyFragment2;
    private LazyFragment3 mLazyFragment3;
    private LazyFragment4 mLazyFragment4;
    private MyPagerAdapter mAdapter;

    @Override
    public int setLayoutId() {
        return R.layout.activity_viewpager;
    }

    @Override
    public void initView() {
        mViewpager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tab_layout);
        mFragmentList = new ArrayList<>();
        mTitles = new ArrayList<>();
        mTitles.add("第1个");
        mTitles.add("第2个");
        mTitles.add("第3个");
        mTitles.add("第4个");
        mFragmentList.add(mLazyFragment1 = LazyFragment1.create("点了1"));
        mFragmentList.add(mLazyFragment2 = LazyFragment2.create("点了2"));
        mFragmentList.add(mLazyFragment3 = LazyFragment3.create("点了3"));
        mFragmentList.add(mLazyFragment4 = LazyFragment4.create("点了4"));
        mViewpager.setOffscreenPageLimit(4);
        mViewpager.setAdapter(mAdapter = new MyPagerAdapter(getSupportFragmentManager(), mFragmentList, mTitles));
        mTabLayout.setupWithViewPager(mViewpager);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ALog.INSTANCE.d("position==" + position);
                switch (position) {
                    case 0:
                        mLazyFragment1.setTv_name("111111111111111111111");
                        break;
                    case 1:
                        mLazyFragment2.setTv_name("222222222222222222222");
                        break;
                    case 2:
                        mLazyFragment3.setTv_name("3333333333333333333333333333");
                        break;
                    case 3:
                        mLazyFragment4.setTv_name("44444444444444444444");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}

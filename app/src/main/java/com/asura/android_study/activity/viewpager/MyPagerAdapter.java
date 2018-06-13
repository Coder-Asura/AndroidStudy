package com.asura.android_study.activity.viewpager;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Liuxd on 2016/11/12 15:41.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private List<BaseLazyFragment> mFragmentList;
    private List<String> mTitles;

    public MyPagerAdapter(FragmentManager fm, List<BaseLazyFragment> fragmentList, List<String> titles) {
        super(fm);
        this.mFragmentList = fragmentList;
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}

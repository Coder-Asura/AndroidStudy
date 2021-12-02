package com.asura.android_study.ui.bottomnav;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.asura.android_study.R;
import com.asura.android_study.ui.fragtofrag.Fragment1;
import com.asura.android_study.ui.fragtofrag.Fragment2;
import com.asura.android_study.ui.fragtofrag.Fragment3;
import com.asura.android_study.ui.fragtofrag.Fragment4;
import com.asura.android_study.ui.fragtofrag.MyListener;
import com.asura.android_study.ui.base.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Created by Liuxd on 2016/11/3 20:11.
 */

public class BottomNavActivity extends BaseActivity implements MyListener {
    private MenuItem lastItem;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;

    @Override
    public int setLayoutId() {
        return R.layout.activity_bottom_nav;
    }

    @Override
    public void initView() {
        //        mNav.getMenu().add(1,0,0,"哈");
        //        mNav.getMenu().add(1,1,1,"哈哈");
        //        mNav.getMenu().add(1,2,2,"哈哈哈");
        //        mNav.getMenu().add(1,3,3,"呵呵");
        //        mNav.getMenu().add(1,4,4,"呵");
        BottomNavigationView nav = findViewById(R.id.nav);
        lastItem = nav.getMenu().getItem(0);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (lastItem != item) {
                    lastItem = item;
                    switch (item.getItemId()) {
                        case R.id.item1:
                            changeFragment(0);
                            break;
                        case R.id.item2:
                            changeFragment(1);
                            break;
                        case R.id.item3:
                            changeFragment(2);
                            break;
                        case R.id.item4:
                            changeFragment(3);
                            break;
                        default:
                    }
                    return true;
                } else {
                    return false;
                }
                //                Toast.makeText(BottomNavActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                //                return true;
            }
        });
        changeFragment(0);
    }

    private void changeFragment(int i) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideAllFragments(transaction);
        switch (i) {
            case 0:
                if (fragment1 == null) {
                    fragment1 = new Fragment1();
                    transaction.add(R.id.content, fragment1);
                } else {
                    transaction.show(fragment1);
                }
                break;
            case 1:
                if (fragment2 == null) {
                    fragment2 = new Fragment2();
                    transaction.add(R.id.content, fragment2);
                } else {
                    transaction.show(fragment2);
                }
                break;
            case 2:
                if (fragment3 == null) {
                    fragment3 = new Fragment3();
                    transaction.add(R.id.content, fragment3);
                } else {
                    transaction.show(fragment3);
                }
                break;
            case 3:
                if (fragment4 == null) {
                    fragment4 = new Fragment4();
                    transaction.add(R.id.content, fragment4);
                } else {
                    transaction.show(fragment4);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideAllFragments(FragmentTransaction transaction) {
        if (fragment1 != null && !fragment1.isHidden()) {
            transaction.hide(fragment1);
        }
        if (fragment2 != null && !fragment2.isHidden()) {
            transaction.hide(fragment2);
        }
        if (fragment3 != null && !fragment3.isHidden()) {
            transaction.hide(fragment3);
        }
        if (fragment4 != null && !fragment4.isHidden()) {
            transaction.hide(fragment4);
        }
    }

    @Override
    public void sendFrom1(String info) {

    }

    @Override
    public void sendFrom2(String info) {

    }
}

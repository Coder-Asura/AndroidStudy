package com.asura.jsoup_demo.ui.main

import android.content.Intent
import android.net.Uri
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.GestureDetector
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import com.asura.jsoup_demo.R
import com.asura.jsoup_demo.base.BaseActivity
import com.asura.jsoup_demo.config.Constant
import com.asura.jsoup_demo.ui.main.new.NewGirlFragment
import com.asura.jsoup_demo.util.AppUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*


class MainActivity : BaseActivity<IMainView, MainPresenter>(), IMainView, NavigationView.OnNavigationItemSelectedListener {
    private var newGirlFragment: NewGirlFragment? = null
    private var currentIndex: Int = FragmentFactory.INDEX_FRAGMENT_NEW_GIRL
    var isToolbarFirstClick = true

    override fun getLayoutId(): Int {
        return R.layout.activity_main;
    }

    override fun initViews() {
        loadMenuData()
    }

    override fun initListener() {
        super.initListener()
        nav_view.setNavigationItemSelectedListener(this)
//        nav_header.setOnClickListener(this)
        toolbar.setOnClickListener(this)
    }

    override fun initDatas() {
        super.initDatas()
        setSelect(currentIndex)
    }

    override fun onClick(v: View?) {
        when (v) {
            toolbar -> {
                if (isToolbarFirstClick) {
                    toastShort(R.string.tip_double_click)
                    isToolbarFirstClick = false
                }
            }
            nav_header -> {
                val uri = Uri.parse(Constant.URL_GITHUB)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        }
    }

    override fun switchContent(position: Int) {
    }

    override fun createPresenter(): MainPresenter {
        return MainPresenter()
    }

    private fun loadMenuData() {
        val toggle = ActionBarDrawerToggle(this, drawer_layout,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        // 双击 666
        val detector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {
                quickToTop()
                return super.onDoubleTap(e)
            }
        })

        toolbar.setOnTouchListener(View.OnTouchListener { v, event ->
            detector.onTouchEvent(event)
            false
        })
    }

    /**
     * 快速返回头部
     */
    private fun quickToTop() {
        when (currentIndex) {
            FragmentFactory.INDEX_FRAGMENT_NEW_GIRL -> newGirlFragment?.quickToTop()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_github -> {
                val uri = Uri.parse(Constant.URL_GITHUB)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            R.id.nav_jianshu -> {
                val uri = Uri.parse(Constant.URL_JIANSHU)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            R.id.nav_weibo -> {
                val uri = Uri.parse(Constant.URL_WEIBO)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            R.id.nav_clear_cache -> {
                toastShort("清缓存")
            }
            R.id.nav_update -> {
                toastShort("没更新，老表")
            }
            R.id.nav_about -> {
                val version = AppUtils.getAppVersion(this)
                toastShort("App 版本：V$version")
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START, true)
        return true
    }

    /**
     * 根据设置的编号显示对应的碎片
     */
    private fun setSelect(index: Int) {
        currentIndex = index
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        hideFragment(transaction)
        // 设置内容区域
        when (index) {
            0 -> if (newGirlFragment == null) {
                newGirlFragment = FragmentFactory
                        .createFragment(FragmentFactory.INDEX_FRAGMENT_NEW_GIRL) as NewGirlFragment
                transaction.add(R.id.main_content, newGirlFragment, FragmentFactory.TAG_FRAGMENT_NEW_GIRL)
            } else {
                transaction.show(newGirlFragment)
            }
            1 -> {

            }
        }
        transaction.commitAllowingStateLoss()
    }

    /**
     * 隐藏碎片
     *
     * @param transaction 碎片事务
     */
    private fun hideFragment(transaction: FragmentTransaction) {
        if (newGirlFragment != null) {
            transaction.hide(newGirlFragment)
        }
    }
}



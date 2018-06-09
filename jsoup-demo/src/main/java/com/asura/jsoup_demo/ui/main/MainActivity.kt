package com.asura.jsoup_demo.ui.main

import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import android.view.View
import com.asura.jsoup_demo.R
import com.asura.jsoup_demo.base.BaseActivity
import com.asura.jsoup_demo.ui.main.new.NewGirlFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<IMainView, MainPresenter>(), IMainView, NavigationView.OnNavigationItemSelectedListener {
    private var newGirlFragment: NewGirlFragment? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_main;
    }

    override fun initViews() {
        loadMenuData()
    }

    override fun initListener() {
        super.initListener()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun initDatas() {
        super.initDatas()
        setSelect(FragmentFactory.INDEX_FRAGMENT_NEW_GIRL)
    }

    override fun onClick(v: View?) {
    }

    override fun switchContent(position: Int) {
    }

    override fun createPresenter(): MainPresenter {
        return MainPresenter()
    }

    private fun loadMenuData() {

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            R.id.nav_github -> setSelect(FragmentFactory.INDEX_FRAGMENT_NEW_GIRL)
//            R.id.nav_exist_goods -> setSelect(FragmentFactory.INDEX_FRAGMENT_EXIST_GOODS)
//            R.id.nav_about -> showMessageDialog(R.string.about, String.format(UiUtils.getString(R.string.about_msg),
//                    AppUtils.getAppVersion(this@MainActivity)), R.string.I_know)
//            R.id.nav_update -> initGoodsInfo()
//            R.id.nav_change_account -> showCommonDialog(R.string.change_account, -1,
//                    UiUtils.getString(R.string.change_account_msg),
//                    R.string.cancel, R.string.confirm, null,
//                    View.OnClickListener {
//                        PreferencesUtils.setLogin(false)
//                        PreferencesUtils.clear(Constant.SP_NAME_USER)
//                        toastShort(R.string.login_out)
//                        openActivity(LoginActivity::class.java)
//                        finish()
//                    })
        }
        drawer_layout.closeDrawer(GravityCompat.START, true)
        return true
    }

    /**
     * 根据设置的编号显示对应的碎片
     */
    private fun setSelect(index: Int) {
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

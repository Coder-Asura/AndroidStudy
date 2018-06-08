package com.asura.jsoup_demo.ui.main.beautiful

import android.view.View
import com.asura.jsoup_demo.R
import com.asura.jsoup_demo.base.BaseFragment
import com.asura.jsoup_demo.bean.BeautifulGirl

/**
 * @author Created by Asura on 2018/6/8 11:26.
 */
class BeautifulGirlFragment : BaseFragment<IBeautifulGirlView, BeautifulGirlPresenter>(), IBeautifulGirlView {
    override fun getLayoutId(): Int {
        return R.layout.fragment_exist_goods;
    }

    override fun initViews(rootView: View) {
        getPresenter().loadList(false, 0, 10)
    }

    override fun showLoadingView() {

    }

    override fun showEmptyView() {

    }

    override fun showErrorView() {
    }

    override fun showContentView() {
    }

    override fun showData(data: List<BeautifulGirl>) {
    }

    override fun loadData(loadMore: Boolean, page: Int, size: Int) {
        getPresenter().loadList(loadMore, page, size)
    }

    override fun createPresenter(): BeautifulGirlPresenter {
        return BeautifulGirlPresenter(getAc())
    }
}
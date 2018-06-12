package com.asura.jsoup_demo.mvp.ui.lce

import com.asura.mvp_framework.base.view.IMvpView

/**
 * @author Created by Asura on 2018/6/12 14:28.
 */
/**
 * @author Created by Asura on 2018/6/8 13:51.
 */
interface ILCEView<T> : IMvpView {

    fun showLoadingView()

    fun showEmptyView()

    fun showErrorView()

    fun showContentView()

    fun showData(data: List<T>)

    fun loadData(loadMore: Boolean, page: Int, size: Int)
}
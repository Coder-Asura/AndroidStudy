package com.asura.jsoup_demo.mvp.ui.lce

import com.asura.jsoup_demo.mvp.IView

/**
 * @author Created by Asura on 2018/6/8 13:51.
 */
interface ILCEView<T> :IView{

    fun showLoadingView()

    fun showEmptyView()

    fun showErrorView()

    fun showContentView()

    fun showData(data: List<T>)

    fun loadData(loadMore: Boolean, page: Int, size: Int)
}
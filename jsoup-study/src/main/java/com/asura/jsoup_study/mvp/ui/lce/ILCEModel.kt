package com.asura.jsoup_study.mvp.ui.lce

import com.asura.jsoup_study.base.HttpCallback

/**
 * @author Created by Asura on 2018/6/8 14:01.
 */
interface ILCEModel<T> {

    fun loadData(loadMore: Boolean, page: Int, size: Int, httpCallback: HttpCallback<T>)

}
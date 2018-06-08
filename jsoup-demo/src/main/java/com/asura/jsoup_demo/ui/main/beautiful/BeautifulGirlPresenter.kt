package com.asura.jsoup_demo.ui.main.beautiful

import com.asura.jsoup_demo.mvp.BasePresenter
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * @author Created by Asura on 2018/6/8 17:13.
 */
class BeautifulGirlPresenter : BasePresenter<IBeautifulGirlView> {
    lateinit var model: BeautifulGirlModel

    constructor(activity: RxAppCompatActivity) {
        model = BeautifulGirlModel(activity)
    }

    fun loadList(loadMore: Boolean, page: Int, size: Int) {
        model.loadData(false, 0, 10)
    }
}

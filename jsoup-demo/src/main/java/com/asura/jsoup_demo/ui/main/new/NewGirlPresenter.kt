package com.asura.jsoup_demo.ui.main.new

import com.asura.jsoup_demo.base.HttpCallback
import com.asura.jsoup_demo.bean.NewGirl
import com.asura.jsoup_demo.mvp.BasePresenter
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * @author Created by Asura on 2018/6/8 17:13.
 */
class NewGirlPresenter : BasePresenter<INewGirlView> {
    lateinit var model: NewGirlModel

    constructor(activity: RxAppCompatActivity) {
        model = NewGirlModel(activity)
    }

    fun loadList(loadMore: Boolean, page: Int, size: Int) {
        model.loadData(false, page, size, object : HttpCallback<List<NewGirl>> {
            override fun onSuccess(data: List<NewGirl>) {
//                getView().showContentView()
                getView().showData(data)
            }

            override fun onFailed(msg: String?, e: Throwable) {
                getView().showErrorView()
            }

        })
    }
}

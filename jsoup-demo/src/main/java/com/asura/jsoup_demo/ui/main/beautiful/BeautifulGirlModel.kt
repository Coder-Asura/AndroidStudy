package com.asura.jsoup_demo.ui.main.beautiful

import com.asura.jsoup_demo.bean.BeautifulGirl
import com.asura.jsoup_demo.retrofit.HttpUtil
import com.asura.jsoup_demo.util.ALog
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.SoftReference


/**
 * @author Created by Asura on 2018/6/8 14:05.
 */
class BeautifulGirlModel : IBeautifulGirlModel {
    private var rxAppCompatActivity: SoftReference<RxAppCompatActivity>? = null

    constructor(activity: RxAppCompatActivity) {
        this.rxAppCompatActivity = SoftReference<RxAppCompatActivity>(activity)
    }

    override fun parseHtml2List(html: String): List<BeautifulGirl> {
        ALog.d("leixing",html);
        return listOf()
    }

    override fun loadData(loadMore: Boolean, page: Int, size: Int) {
        HttpUtil.getApiService().getBeautifulGirl()
                .compose(rxAppCompatActivity?.get()?.bindUntilEvent(ActivityEvent.PAUSE))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { html -> parseHtml2List(html) }
                .subscribe(object : Observer<List<BeautifulGirl>> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: List<BeautifulGirl>) {
                        ALog.d("5555555555555555555555555555")
                        ALog.d(t)
                    }

                    override fun onError(e: Throwable) {
                        ALog.e("dsd",e)
                    }

                })
    }


}
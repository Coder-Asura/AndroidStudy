package com.asura.jsoup_demo.ui.main.new

import com.asura.jsoup_demo.base.HttpCallback
import com.asura.jsoup_demo.bean.NewGirl
import com.asura.jsoup_demo.retrofit.HttpUtil
import com.asura.jsoup_demo.util.ALog
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.FragmentEvent
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import java.lang.ref.SoftReference
import java.util.*


/**
 * @author Created by Asura on 2018/6/8 14:05.
 */
class NewGirlModel : INewGirlModel<List<NewGirl>> {
    private var provider: SoftReference<LifecycleProvider<FragmentEvent>>? = null

    constructor(provider: LifecycleProvider<FragmentEvent>) {
        this.provider = SoftReference<LifecycleProvider<FragmentEvent>>(provider)
    }

    override fun parseHtml2List(html: String): List<NewGirl> {
//        ALog.d("html", html);
        val newGirls = ArrayList<NewGirl>()
        try {

            val document = Jsoup.parse(html)
            val articles = document.select("div#page")
                    .select("div#content")
                    .select("div.container")
                    .select("div.row")
                    .select("div#primary")
                    .select("main#main")
                    .select("div.posts-layout")
                    .select("article.post")
            ALog.d("girls", articles.size)
            for (article in articles) {
                val girlElement = article
                        .select("div.post-type-fullwidth")
                        .select("div.post-wrapper")
                        .select("div.entry-thumb")
                        .select("a")
                val title = girlElement.attr("title")
                val imgUrl = girlElement.select("img").attr("src")
                val link = girlElement.attr("href")
                val girl = NewGirl(title, imgUrl, link, null)
                newGirls.add(girl)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        ALog.d("girls", newGirls.size)
        ALog.d("girls", newGirls)
        return newGirls
    }

    override fun loadData(loadMore: Boolean, page: Int, size: Int, httpCallback: HttpCallback<List<NewGirl>>) {
        HttpUtil.getApiService()
                .getBeautifulGirl(page)
                .compose(provider?.get()?.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { html ->
                    parseHtml2List(html)
                }
                .subscribe(object : Observer<List<NewGirl>> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(data: List<NewGirl>) {
                        httpCallback.onSuccess(data)
                    }

                    override fun onError(e: Throwable) {
                        httpCallback.onFailed(e.message, e)
                    }
                })
    }


}
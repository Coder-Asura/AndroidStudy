package com.asura.jsoup_study.api

import com.asura.jsoup_study.config.Constant
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @author Created by Asura on 2018/6/8 14:19.
 */
interface ApiService {
    @GET(value = Constant.URL_NEW_GIRL)
    fun getBeautifulGirl(@Path("page") page: Int): Observable<String>;
}
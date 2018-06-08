package com.asura.jsoup_demo.api

import com.asura.jsoup_demo.config.Constant
import io.reactivex.Observable
import retrofit2.http.GET



/**
 * @author Created by Asura on 2018/6/8 14:19.
 */
interface ApiService {
    @GET(value = Constant.URL_BEAUTIFUL_GIRL)
    fun getBeautifulGirl(): Observable<String>;
}
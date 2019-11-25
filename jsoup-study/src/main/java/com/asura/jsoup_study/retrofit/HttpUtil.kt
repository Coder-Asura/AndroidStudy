package com.asura.jsoup_study.retrofit

import android.util.Log
import com.asura.jsoup_study.BuildConfig
import com.asura.jsoup_study.api.ApiService
import com.asura.jsoup_study.config.Constant
import com.asura.jsoup_study.converter.CustomGsonConverterFactory
import com.asura.jsoup_study.util.ALog
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Created by Asura on 2018/6/8 14:21.
 */
class HttpUtil {
    companion object {

        private var apiService: ApiService? = null;

        fun getApiService(): ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                        .baseUrl(Constant.BASE_URL)
                        .client(getOkHttpClient())
//                        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                        .addConverterFactory(CustomGsonConverterFactory.create(GsonBuilder().setLenient().create()))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                        .create(ApiService::class.java)
            }
            return apiService!!
        }

        private fun getOkHttpClient(): OkHttpClient {
            val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            builder.addInterceptor(getHttpLoggingInterceptor())
                    .connectTimeout(10 * 1000L, TimeUnit.MILLISECONDS)
                    .writeTimeout(10 * 1000L, TimeUnit.MILLISECONDS)
                    .readTimeout(10 * 1000L, TimeUnit.MILLISECONDS)
//                    .sslSocketFactory(SSLContextUtil.getDefaultSLLContext().getSocketFactory(),
//                            SSLContextUtil.trustManagers as X509TrustManager)
//                    .hostnameVerifier(SSLContextUtil.HOSTNAME_VERIFIER)
            return builder.build();
        }

        private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
            //日志显示级别
            val level = if (BuildConfig.LOG_ENABLE) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            //新建log拦截器
            val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    ALog.d("RxRetrofit", message)
                }
            })
            loggingInterceptor.level = level
            return loggingInterceptor
        }
    }

}


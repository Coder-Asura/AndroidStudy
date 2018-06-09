package com.asura.jsoup_demo.retrofit

import android.util.Log
import com.asura.jsoup_demo.BuildConfig
import com.asura.jsoup_demo.api.ApiService
import com.asura.jsoup_demo.config.Constant
import com.asura.jsoup_demo.converter.CustomGsonConverterFactory
import com.asura.jsoup_demo.util.ALog
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
            val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                ALog.d("RxRetrofit", message)
            })
            loggingInterceptor.level = level
            return loggingInterceptor
        }
    }

}


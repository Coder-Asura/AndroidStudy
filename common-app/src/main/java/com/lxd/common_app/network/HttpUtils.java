package com.lxd.common_app.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lxd.common_app.App;
import com.lxd.common_app.utils.LogUtil;
import com.lxd.common_app.utils.SSLContextUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Liuxd on 2016/9/23 11:21.
 * 网络请求工具类
 * <p>
 * 封装retrofit+okHttp
 */

public class HttpUtils {
    private ApiService mApiService;

    //    private static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private static final String BASE_URL = "https://ble.litetrace.com.cn";
    private static final int CONNECT_TIMEOUT = 10;
    private static final int READ_TIMEOUT = 20;
    private static final File CACHE_PATH = App.getInstance().getCacheDir();
    private static final int MAX_CACHE_SIZE = 20 * 1024 * 1024;

    private HttpUtils() {
        LogUtil.d("初始化retrofit:", CACHE_PATH.getAbsolutePath());
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .cache(new Cache(CACHE_PATH, MAX_CACHE_SIZE));
        SSLContext sslContext = SSLContextUtil.getDefaultSLLContext();
        if (sslContext != null) {
            builder.sslSocketFactory(sslContext.getSocketFactory(), SSLContextUtil.getTrustManagers());
        }
        builder.hostnameVerifier(SSLContextUtil.HOSTNAME_VERIFIER);
        OkHttpClient okHttpClient = builder.build();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return mApiService;
    }

    private static class HttpUtilsSingleton {
        private static final HttpUtils INSTANCE = new HttpUtils();
    }

    public static HttpUtils getInstance() {
        return HttpUtilsSingleton.INSTANCE;
    }

}

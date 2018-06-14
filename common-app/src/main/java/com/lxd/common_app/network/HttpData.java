package com.lxd.common_app.network;

import com.lxd.common_app.entity.HttpResult;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.Function;


/**
 * Created by Liuxd on 2016/11/17 22:19.
 */

public class HttpData {

//    public static void getVersion(String pin, Observer<Pin> observer) {
//        Observable<Pin> observable = HttpUtils.getInstance().getApiService().getVersion(pin).map(new HttpResultFunc<Pin>());
//        setSubscribe(observable, observer);
//    }


    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {

    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private static class HttpResultFunc<T> implements Function<HttpResult<T>, T> {

        @Override
        public T apply(HttpResult<T> httpResult) {
            if (httpResult.getResCode() != 1) {
                throw new ApiException(httpResult);
            }
            return httpResult.getData();
        }
    }
}

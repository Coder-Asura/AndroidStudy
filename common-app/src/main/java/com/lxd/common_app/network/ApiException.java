package com.lxd.common_app.network;

import com.lxd.common_app.entity.HttpResult;

/**
 * Created by Liuxd on 2016/11/17 22:01.
 */

public class ApiException extends RuntimeException {
    public ApiException(HttpResult httpResult) {
        this(getApiExceptionMessage(httpResult));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 对服务器接口传过来的错误信息进行统一处理
     * 免除在Activity的过多的错误判断
     */
    private static String getApiExceptionMessage(HttpResult httpResult){
        String message = "";
        switch (httpResult.getResCode()) {
            case 0:
                message = "ERROR:解析失败";
                break;
            case -1:
                message = "ERROR:服务器通讯故障";
                break;
            default:
                message = "ERROR:网络连接异常";

        }
        return message;
    }
}

package com.lxd.common_app.entity;

/**
 * Created by Liuxd on 2016/11/17 22:11.
 * 接口请求返回结果实体父类
 */

public class HttpResult<T> {
    private String resMsg;//提示语
    private int resCode;//结果码
    private T data;

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

package com.asura.android_study.activity.multitype.base;

import androidx.annotation.NonNull;

/**
 * Created by Liuxd on 2017/7/20 13:29.
 */
public class Advertisement {
    @NonNull
    private String title;
    @NonNull
    private String detail;
    @NonNull
    private String imgUrl;

    public Advertisement(@NonNull String title, @NonNull String detail, @NonNull String imgUrl) {
        this.title = title;
        this.detail = detail;
        this.imgUrl = imgUrl;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getDetail() {
        return detail;
    }

    public void setDetail(@NonNull String detail) {
        this.detail = detail;
    }

    @NonNull
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(@NonNull String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

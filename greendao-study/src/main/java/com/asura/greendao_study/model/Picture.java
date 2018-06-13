package com.asura.greendao_study.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Liuxd on 2016/9/8 17:17.
 */
@Entity
public class Picture {
    @Id
    private long pictureId;

    @Generated(hash = 951364883)
    public Picture(long pictureId) {
        this.pictureId = pictureId;
    }

    @Generated(hash = 1602548376)
    public Picture() {
    }

    public long getPictureId() {
        return this.pictureId;
    }

    public void setPictureId(long pictureId) {
        this.pictureId = pictureId;
    }
}

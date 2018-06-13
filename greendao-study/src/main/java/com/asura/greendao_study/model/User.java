package com.asura.greendao_study.model;


import com.asura.greendao_study.dao.DaoSession;
import com.asura.greendao_study.dao.PictureDao;
import com.asura.greendao_study.dao.UserDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;


/**
 * Created by Liuxd on 2016/9/7 17:57.
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private int age;
    private boolean isBoy;
    private long pictureId;
    @ToOne (joinProperty = "pictureId")
    private Picture picture;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1507654846)
    private transient UserDao myDao;
    @Generated(hash = 1986840853)
    private transient Long picture__resolvedKey;

    @Generated(hash = 2039164071)
    public User(Long id, String name, int age, boolean isBoy, long pictureId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isBoy = isBoy;
        this.pictureId = pictureId;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getIsBoy() {
        return this.isBoy;
    }

    public void setIsBoy(boolean isBoy) {
        this.isBoy = isBoy;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isBoy=" + isBoy +
                '}';
    }

    public long getPictureId() {
        return this.pictureId;
    }

    public void setPictureId(long pictureId) {
        this.pictureId = pictureId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 545923159)
    public Picture getPicture() {
        long __key = this.pictureId;
        if (picture__resolvedKey == null || !picture__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PictureDao targetDao = daoSession.getPictureDao();
            Picture pictureNew = targetDao.load(__key);
            synchronized (this) {
                picture = pictureNew;
                picture__resolvedKey = __key;
            }
        }
        return picture;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 330863195)
    public void setPicture(@NotNull Picture picture) {
        if (picture == null) {
            throw new DaoException(
                    "To-one property 'pictureId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.picture = picture;
            pictureId = picture.getPictureId();
            picture__resolvedKey = pictureId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2059241980)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }
}

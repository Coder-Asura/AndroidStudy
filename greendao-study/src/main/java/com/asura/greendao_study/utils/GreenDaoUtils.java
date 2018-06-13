package com.asura.greendao_study.utils;

import android.database.sqlite.SQLiteDatabase;

import com.asura.greendao_study.dao.DaoMaster;
import com.asura.greendao_study.dao.DaoSession;
import com.asura.greendao_study.application.MyApplication;


/**
 * Created by Liuxd on 2016/9/7 17:58.
 */
public class GreenDaoUtils {
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private static GreenDaoUtils greenDaoUtils;

    private GreenDaoUtils() {
    }

    public static GreenDaoUtils getSingleTon() {
        if (greenDaoUtils == null) {
            greenDaoUtils = new GreenDaoUtils();
        }
        return greenDaoUtils;
    }

    private void initGreenDao() {
        mHelper = new DaoMaster.DevOpenHelper(MyApplication.getApplication(), "test-db", null);
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getmDaoSession() {
        if (mDaoMaster == null) {
            initGreenDao();
        }
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        if (db == null) {
            initGreenDao();
        }
        return db;
    }
}

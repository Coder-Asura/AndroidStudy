package com.asura.android_study.activity.threadpool.step1.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.asura.android_study.activity.threadpool.step1.CommandQueue;
import com.asura.android_study.activity.threadpool.step1.ThreadPoolUtil;
import com.asura.android_study.activity.threadpool.step1.runable.WriteCommandRunnable;

/**
 * Created by Liuxd on 2016/10/21 21:23.
 */

public class CommandService extends Service {
    CommandBinder mCommandBinder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (mCommandBinder == null) {
            mCommandBinder = new CommandBinder();
        }
        return mCommandBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("lxd", "CommandService onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("lxd", "CommandService onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("lxd", "CommandService onDestroy()");
        super.onDestroy();
    }

    public class CommandBinder extends Binder {
        public CommandService getService() {
            return CommandService.this;
        }

        public void addToQueue(String msg) {
            CommandQueue.getInstance().offerMsg(msg);
            Log.i("lxd", "CommandService addToQueue()  " + msg);
        }

        public void addToQueue1(String msg) {
            CommandQueue.getInstance().offerMsg(msg);
            ThreadPoolUtil.getPoolInstance().executeTask(new WriteCommandRunnable());
            Log.i("lxd", "CommandService addToQueue1()  " + msg);
        }

        public void takeFromQueue() {
            ThreadPoolUtil.getPoolInstance().executeTask(new WriteCommandRunnable());
        }

        public void reset() {
//            ThreadPoolUtil.getPoolInstance().exitAllThread();
            CommandQueue.getInstance().clearQueue();
        }
    }

}

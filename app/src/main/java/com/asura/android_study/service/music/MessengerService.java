package com.asura.android_study.service.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * Created by Liuxd on 2017/6/16 13:27.
 */
public class MessengerService extends Service {
    private MyHandler mMyHandler;
    public Messenger mMessenger;
    private MediaPlayer mPlayer;

    int currentPos = 0;

    public static final int MSG_PLAY = 1;
    public static final int MSG_PAUSE = 2;
    public static final int MSG_TIME = 3;
    private static final String TAG = "test";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        mMyHandler = new MyHandler(this);
        mMessenger = new Messenger(mMyHandler);

        mPlayer = new MediaPlayer();
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                Message message = Message.obtain(null, MessengerActivity.MSG_MUSIC_END);
                try {
                    mMessenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return mMessenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlayer.reset();
        mPlayer.release();
    }

    public void playMusic(Message msg) {
        if (mPlayer.isPlaying()) {
            return;
        }

        try {
            mPlayer.reset();
            mPlayer.setDataSource((String) msg.obj);
            mPlayer.prepare();
            mPlayer.seekTo(currentPos);
            mPlayer.start();
            Message message = Message.obtain(msg);
            message.what = MessengerActivity.MSG_MUSIC_START;
            message.obj = mPlayer.getDuration();
            try {
                message.replyTo.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            replyTime(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pauseMusic(Message msg) {
        if (mPlayer.isPlaying()) {
            currentPos = mPlayer.getCurrentPosition();
            mPlayer.pause();
            Message message = Message.obtain(msg);
            message.what = MessengerActivity.MSG_MUSIC_PAUSE;
            try {
                msg.replyTo.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void replyTime(Message msg) {
        if (!mPlayer.isPlaying()) {
            return;
        }
        Message message = Message.obtain(msg);
        message.what = MessengerActivity.MSG_MUSIC_TIME;
        message.obj = mPlayer.getCurrentPosition();
        try {
            msg.replyTo.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Message message1 = Message.obtain(msg);
        message1.what = MSG_TIME;
        message1.obj = mPlayer.getCurrentPosition();
        message1.replyTo = msg.replyTo;

        //隔1秒回复一次
        mMyHandler.sendMessageDelayed(message1, 1000);
    }

    private static class MyHandler extends Handler {
        WeakReference<MessengerService> mServiceWeakReference;

        public MyHandler(MessengerService service) {
            this.mServiceWeakReference = new WeakReference<MessengerService>(service);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MessengerService service = mServiceWeakReference.get();
            if (service == null) {
                return;
            }
            switch (msg.what) {
                case MSG_PLAY:
                    service.playMusic(msg);
                    break;
                case MSG_TIME:
                    service.replyTime(msg);
                    break;
                case MSG_PAUSE:
                    service.pauseMusic(msg);
                    break;
                default:
            }
        }
    }
}

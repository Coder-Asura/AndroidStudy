package com.asura.android_study.ui.service.music;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;

import com.asura.android_study.R;

/**
 * Created by Liuxd on 2017/6/15 15:34.
 */
public class MusicService extends Service {
    private MediaPlayer mPlayer;

    private IBinder binder = new MyBinder();
    int currentPos = 0;

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = new MediaPlayer();
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent = new Intent("com.music.end");
                intent.putExtra("hint", "音乐播放完毕");
                sendBroadcast(intent);
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlayer.reset();
        mPlayer.release();
    }

    public void playMusic(String path) {
        if (isPlaying()) {
            return;
        }

        try {
            mPlayer.reset();
            if (!TextUtils.isEmpty(path)) {
                mPlayer.setDataSource(path);
            } else {
                AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.youhebuke);
                mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getDeclaredLength());
            }
            mPlayer.prepare();
            mPlayer.start();
            mPlayer.seekTo(currentPos);
            //			Intent intent = new Intent("com.music.duration");
            //			intent.putExtra("duration", getDuration());
            //			sendBroadcast(intent);
            //
            //			mHandler.sendEmptyMessageDelayed(0, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pauseMusic() {
        if (isPlaying()) {
            currentPos = mPlayer.getCurrentPosition();
            mPlayer.pause();
        }
    }

    public void stopMusic() {
        if (isPlaying()) {
            mPlayer.stop();
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    if (mPlayer.isPlaying()) {
                        Intent intent = new Intent("com.music.current");
                        intent.putExtra("current", getCurrentPosition());
                        sendBroadcast(intent);
                        mHandler.sendEmptyMessageDelayed(0, 1000);
                    }
                    break;

                default:
            }
        }

        ;
    };

    public int getDuration() {
        return mPlayer.getDuration();
    }

    public int getCurrentPosition() {
        return mPlayer.getCurrentPosition();
    }

    public boolean isPlaying() {
        return mPlayer.isPlaying();
    }

    public final class MyBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }
}

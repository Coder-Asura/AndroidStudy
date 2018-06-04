package com.asura.promote.service.music;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.asura.promote.R;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * Created by Liuxd on 2017/6/16 13:32.
 * 用Messenger和Service互相通信
 */
public class MessengerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mStartBtn;
    private Button mPauseBtn;
    private TextView mTotalTime;
    private TextView mPlayTime;

    private String musicPath;

    private MyHandler mMyHandler;
    public Messenger mMessenger;
    /**
     * 向Service发送Message的Messenger对象
     */
    Messenger mService = null;

    /**
     * 判断有没有绑定Service
     */
    boolean mBound;

    public static final int MSG_MUSIC_START = 4;
    public static final int MSG_MUSIC_PAUSE = 5;
    public static final int MSG_MUSIC_TIME = 6;
    public static final int MSG_MUSIC_END = 7;
    private static final String TAG = "test";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        mTotalTime = (TextView) findViewById(R.id.music_total_time);
        mPlayTime = (TextView) findViewById(R.id.music_play_time);
        mStartBtn = (Button) findViewById(R.id.start_music);
        mPauseBtn = (Button) findViewById(R.id.pause_music);
        mStartBtn.setOnClickListener(this);
        mPauseBtn.setOnClickListener(this);
        new Thread() {
            @Override
            public void run() {
                getFirstMusic(Environment.getExternalStorageDirectory().getAbsolutePath());
            }
        }.start();
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.d(TAG, "onServiceConnected: ");
            // Activity已经绑定了Service
            // 通过参数service来创建Messenger对象，这个对象可以向Service发送Message，与Service进行通信
            mService = new Messenger(service);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            Log.d(TAG, "onServiceDisconnected: ");
            mService = null;
            mBound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        // 绑定Service
        bindService(new Intent(this, MessengerService.class), mConnection,
                Context.BIND_AUTO_CREATE);
        mMyHandler = new MyHandler(MessengerActivity.this);
        mMessenger = new Messenger(mMyHandler);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 解绑
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_music:
                if (!mBound) return;
                // 向Service发送一个Message
                Message msg = Message.obtain(null, MessengerService.MSG_PLAY, 0, 0);
                msg.obj = musicPath;
                msg.replyTo = mMessenger;
                try {
                    mService.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.pause_music:
                if (!mBound) return;
                // 向Service发送一个Message
                Message message = Message.obtain(null, MessengerService.MSG_PAUSE, 0, 0);
                message.obj = musicPath;
                message.replyTo = mMessenger;
                try {
                    mService.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                break;
            default:
        }
    }

    private void getFirstMusic(String path) {
        File file = new File(path);
        if (file.isFile()) {
            if (file.getName().endsWith(".mp3")) {
                musicPath = file.getAbsolutePath();
            }
        } else {
            File[] childs = file.listFiles();
            if (childs != null && childs.length > 0) {
                for (File f : childs) {
                    getFirstMusic(f.getAbsolutePath());
                }
            }
        }
    }

    public static class MyHandler extends Handler {
        WeakReference<MessengerActivity> mServiceWeakReference;

        public MyHandler(MessengerActivity activity) {
            this.mServiceWeakReference = new WeakReference<MessengerActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MessengerActivity activity = mServiceWeakReference.get();
            if (activity == null) {
                return;
            }
            switch (msg.what) {
                case MSG_MUSIC_START:
                    Toast.makeText(activity, "播放开始", Toast.LENGTH_SHORT).show();
                    activity.showTotalTime((int) msg.obj);
                    break;
                case MSG_MUSIC_PAUSE:
                    Toast.makeText(activity, "播放暂停", Toast.LENGTH_SHORT).show();
                    break;
                case MSG_MUSIC_TIME:
                    activity.showTime((int) msg.obj);
                    break;
                case MSG_MUSIC_END:
                    Toast.makeText(activity, "播放完毕", Toast.LENGTH_SHORT).show();
                    break;
                default:
            }
        }
    }

    private void showTotalTime(int time) {
        mTotalTime.setText(musicPath + "\n音乐总时间：" + time / 1000 + "s");
    }

    private void showTime(int time) {
        mPlayTime.setText("播放时间:" + time / 1000 + "s");
    }
}

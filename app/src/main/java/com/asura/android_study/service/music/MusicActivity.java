package com.asura.android_study.service.music;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.asura.android_study.R;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * Created by Liuxd on 2017/6/15 15:35.
 * 播放音乐
 */
public class MusicActivity extends AppCompatActivity implements View.OnClickListener {
    private MusicService service;

    private TextView mTotalTime;
    private TextView mPlayTime;

    private Button mStartBtn;
    private Button mPauseBtn;

    private String musicPath;

    private Handler mHandler;

    private static final int MSG_SCAN_MUSIC = 1;
    private static final int MSG_PLAY_MUSIC = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.music.end");
        filter.addAction("com.music.duration");
        filter.addAction("com.music.current");
        registerReceiver(mReceiver, filter);

        mTotalTime = (TextView) findViewById(R.id.music_total_time);
        mPlayTime = (TextView) findViewById(R.id.music_play_time);
        mStartBtn = (Button) findViewById(R.id.start_music);
        mPauseBtn = (Button) findViewById(R.id.pause_music);
        mStartBtn.setOnClickListener(this);
        mPauseBtn.setOnClickListener(this);

        mHandler = new MyHandler(this);
        mHandler.sendEmptyMessage(MSG_SCAN_MUSIC);
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("test", "onServiceDisconnected");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            Log.e("test", "onServiceConnected");
            service = ((MusicService.MyBinder) binder).getService();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        unbindService(conn);
        unregisterReceiver(mReceiver);
    }

    ;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_music:
                service.playMusic(musicPath);
                mTotalTime.setText(musicPath + "\n音乐总时间：" + service.getDuration() / 1000 + "s");
                mHandler.sendEmptyMessageDelayed(MSG_PLAY_MUSIC, 1000);
                break;
            case R.id.pause_music:
                service.pauseMusic();
                break;

            default:
                break;
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

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("com.music.end")) {
                Toast.makeText(MusicActivity.this, intent.getCharSequenceExtra("hint"), Toast.LENGTH_SHORT).show();
            } else if (action.equals("com.music.duration")) {
                mTotalTime.setText("音乐总时间：" + intent.getIntExtra("duration", 0) / 1000 + "s");
            } else if (action.equals("com.music.current")) {
                mPlayTime.setText("播放时间:" + intent.getIntExtra("current", 0) / 1000 + "s");
            }
        }
    };

    private static class MyHandler extends Handler {
        WeakReference<MusicActivity> mActivityWeakReference;

        public MyHandler(MusicActivity activity) {
            this.mActivityWeakReference = new WeakReference<MusicActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final MusicActivity activity = mActivityWeakReference.get();
            if (activity == null) {
                return;
            }
            switch (msg.what) {
                case MSG_SCAN_MUSIC:
                    new Thread() {
                        @Override
                        public void run() {
                            activity.getFirstMusic(Environment.getExternalStorageDirectory().getAbsolutePath());
                        }
                    }.start();
                    break;
                case MSG_PLAY_MUSIC:
                    if (activity.service.isPlaying()) {
                        activity.mPlayTime.setText("播放时间:" + activity.service.getCurrentPosition() / 1000 + "s");
                        activity.mHandler.sendEmptyMessageDelayed(MSG_PLAY_MUSIC, 1000);
                    }
                    break;

                default:
                    break;
            }
        }
    }
}

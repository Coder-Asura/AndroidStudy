package com.asura.promote.service.music;

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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.asura.promote.R;

import java.io.File;

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

        new Thread(){
            public void run() {
                getFirstMusic(Environment.getExternalStorageDirectory().getAbsolutePath());
            };
        }.start();

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
            service = ((MusicService.MyBinder)binder).getService();
        }
    };

    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    if (service.isPlaying()) {
                        mPlayTime.setText("播放时间:" + service.getCurrentPosition() / 1000 + "s");
                        mHandler.sendEmptyMessageDelayed(1, 1000);
                    }
                    break;

                default:
                    break;
            }
        };
    };

    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        unbindService(conn);
        unregisterReceiver(mReceiver);
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_music:
                service.playMusic(musicPath);
                mTotalTime.setText(musicPath+"音乐总时间：" + service.getDuration() / 1000 + "s");
                mHandler.sendEmptyMessageDelayed(1, 1000);
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
}

package com.asura.android_study.ui.threadpool.step1;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.asura.android_study.R;
import com.asura.android_study.ui.threadpool.step1.service.CommandService;


public class ThreadPool2Activity extends AppCompatActivity {
    private Button bindService, unBindService, add, take, reset;
    private SeekBar seekBar;
    private CommandService.CommandBinder mCommandBinder;
    private int index = 0;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("lxd", "onServiceConnected()");
            mCommandBinder = (CommandService.CommandBinder) service;
            mCommandBinder.addToQueue("哈哈哈");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("lxd", "onServiceDisconnected()" + name);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool);
        bindService = (Button) findViewById(R.id.bind);
        unBindService = (Button) findViewById(R.id.unBind);
        add = (Button) findViewById(R.id.add);
        take = (Button) findViewById(R.id.take);
        reset = (Button) findViewById(R.id.reset);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bindIntent = new Intent(ThreadPool2Activity.this, CommandService.class);
                bindService(bindIntent, mServiceConnection, BIND_AUTO_CREATE);
            }
        });
        unBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(mServiceConnection);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCommandBinder == null)
                    return;
                mCommandBinder.addToQueue("命令" + index);
                index++;
            }
        });
        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCommandBinder == null)
                    return;
                mCommandBinder.takeFromQueue();
                index--;
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 0;
                if (mCommandBinder == null)
                    return;
                mCommandBinder.reset();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mCommandBinder == null)
                    return;
                mCommandBinder.addToQueue1("命令SeekBar" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        Command command = new Command();
        command.age = new CommandText();
        command.name = new Command1();
        Log.i("lxd", (command == null) + "0");
        command.age = null;
        command.name = null;
        Log.i("lxd", (command == null) + "1");
    }
}

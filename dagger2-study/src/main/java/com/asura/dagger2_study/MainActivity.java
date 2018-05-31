package com.asura.dagger2_study;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.asura.dagger2_study.step0.Step0Activity;
import com.asura.dagger2_study.step0.Student;
import com.asura.dagger2_study.step1.Step1Activity;

import javax.inject.Inject;

/**
 * Dagger2 学习
 * <p>
 * 简书链接：https://www.jianshu.com/p/b40bcd1a9ec9
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStep0, btnStep1, btnStep2;
    @Inject
    Student mStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStep0 = findViewById(R.id.btn_step0);
        btnStep1 = findViewById(R.id.btn_step1);
        btnStep2 = findViewById(R.id.btn_step2);
        btnStep0.setOnClickListener(this);
        btnStep1.setOnClickListener(this);
        btnStep2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_step0:
                intent.setClass(MainActivity.this, Step0Activity.class);
                break;
            case R.id.btn_step1:
                intent.setClass(MainActivity.this, Step1Activity.class);
                break;
            case R.id.btn_step2:
                break;
            default:
        }
        startActivity(intent);
    }
}

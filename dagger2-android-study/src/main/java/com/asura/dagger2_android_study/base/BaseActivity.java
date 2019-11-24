package com.asura.dagger2_android_study.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dagger.android.AndroidInjection;

/**
 * @author Created by Asura on 2018/6/4 17:19.
 */
public class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
    }
}

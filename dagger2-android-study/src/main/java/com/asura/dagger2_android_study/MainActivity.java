package com.asura.dagger2_android_study;

import android.os.Bundle;
import android.widget.TextView;

import com.asura.dagger2_android_study.base.BaseActivity;
import com.asura.dagger2_android_study.bean.Student;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {
    @Inject
    Student mStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.tv_student))
                .setText(mStudent.toString());
    }

}

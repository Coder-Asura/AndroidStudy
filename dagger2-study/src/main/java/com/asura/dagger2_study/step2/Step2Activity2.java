package com.asura.dagger2_study.step2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.asura.dagger2_study.R;
import com.asura.dagger2_study.step1.GirlFriend;
import com.asura.dagger2_study.step1.common.ComponentHolder;

import javax.inject.Inject;

/**
 * @author created by asura on 2018/5/31 16:01.
 * 1、appmodule：初始化全局变量
 * 2、appcomponent：注入器，储存了我们要用到的全局变量对象
 * 3、myapplication: 在oncreate()方法中唯一一次初始化了appcomponent对象，并放入了componentholder中。
 * <p>
 * myapplication （作为参数初始化）-> appmodule(初始化全局变量) -> (注入) appcomponent ->(存储到)componentholder
 * <p>
 * 你需要什么对象全局单例，就在提供该对象方法的@Provides注解旁加上一个@Singleton,
 * 并且在该module关联的Component中加上同样的注解
 * <p>
 * 作者：却把清梅嗅2
 * 链接：https://www.jianshu.com/p/c46acc3f21ab
 */
public class Step2Activity2 extends AppCompatActivity {
    private TextView tv1;

    @Inject
    GirlFriend mGirlFriend;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);
        tv1 = findViewById(R.id.tv1);
        inject();
        tv1.setText(mGirlFriend.toString());
    }

    private void inject() {
        DaggerStep2Component2.builder()
                .appComponent(ComponentHolder.getAppComponent())
                .step2Module2(new Step2Module2(this))
                .build()
                .inject(this);
        Log.d("Asura", "注入完毕，mGirlFriend：" + mGirlFriend);
    }
}

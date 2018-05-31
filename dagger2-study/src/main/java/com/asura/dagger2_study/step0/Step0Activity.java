package com.asura.dagger2_study.step0;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.asura.dagger2_study.R;

import javax.inject.Inject;

/**
 * @author Created by Asura on 2018/5/31 13:54.
 * @Inject ： 注入，被注解的构造方法会自动编译生成一个Factory工厂类提供该类对象。
 * @Component: 注入器，类似快递员，作用是将产生的对象注入到需要对象的容器中，供容器使用。
 * @Module: 模块，类似快递箱子，在Component接口中通过@Component(modules =xxxx.class),
 * 将容器需要的商品封装起来，统一交给快递员（Component），让快递员统一送到目标容器中。
 * <p>
 * 作者：却把清梅嗅2
 * 链接：https://www.jianshu.com/p/c46acc3f21ab
 * </p>
 */
public class Step0Activity extends AppCompatActivity {
    private Button btnStudent;
    @Inject
    Student mStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step0);
        DaggerStep0Component
                .builder()
                //如果Student 取消@Inject 注解，并在Module 中提供 @Providers，则需要这个方法，
                //否则此方法被弃用
//                .step0Module(new Step0Module(this))
                .build()
                .inject(this);
        btnStudent = findViewById(R.id.btn_student);
        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Step0Activity.this, mStudent.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

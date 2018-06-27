package com.asura.android_study.activity.multitype.base;

import android.support.annotation.NonNull;

/**
 * Created by Liuxd on 2017/7/20 13:25.
 */
public class Person {
    @NonNull
    private String name;
    private int sex;
    private int age;

    public Person(@NonNull String name, int sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

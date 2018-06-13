package com.asura.android_study.model;

/**
 * Created by Liuxd on 2016/9/14 17:29.
 * 衣服
 */
public class Clothes {
    private String color;
    private String type;
    private String name;

    public Clothes() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

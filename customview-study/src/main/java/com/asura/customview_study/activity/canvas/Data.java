package com.asura.customview_study.activity.canvas;

/**
 * @author Created by Asura on 2018/6/27 15:25.
 */
public class Data {
    private String name;
    private float number;
    private int color;

    public Data(String name, float number, int color) {
        this.name = name;
        this.number = number;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public float getNumber() {
        return number;
    }

    public int getColor() {
        return color;
    }
}

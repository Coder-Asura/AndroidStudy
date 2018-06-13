package com.asura.android_study.model;

import java.util.List;

/**
 * Created by Liuxd on 2016/9/12 17:58.
 * 女朋友
 */
public class GirlFriend {
    private boolean isMan;
    private int age;
    private float height;
    private float weight;
    private String feature;
    private long betrothalGift;
    private List<Clothes> clothes;

    public GirlFriend() {
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public long getBetrothalGift() {
        return betrothalGift;
    }

    public void setBetrothalGift(long betrothalGift) {
        this.betrothalGift = betrothalGift;
    }

    @Override
    public String toString() {
        return "GirlFriend{" +
                "isMan=" + isMan +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", feature='" + feature + '\'' +
                ", betrothalGift=" + betrothalGift +
                ", clothes=" + clothes +
                '}';
    }

    public GirlFriend(boolean isMan, int age, float height, float weight, String feature, long betrothalGift, List<Clothes> clothes) {
        this.isMan = isMan;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.feature = feature;
        this.betrothalGift = betrothalGift;
        this.clothes = clothes;
    }

    public List<Clothes> getClothes() {
        return clothes;
    }

    public void setClothes(List<Clothes> clothes) {
        this.clothes = clothes;
    }
}

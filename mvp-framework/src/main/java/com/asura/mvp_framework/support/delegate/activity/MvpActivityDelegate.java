package com.asura.mvp_framework.support.delegate.activity;

import android.os.Bundle;

import com.asura.mvp_framework.base.presenter.IMvpPresenter;
import com.asura.mvp_framework.base.view.IMvpView;

/**
 * 静态代理：定义 Activity 目标接口
 * <p>
 * 代理生命周期
 *
 * @author Created by Asura on 2018/6/11 10:33.
 */
public interface MvpActivityDelegate<V extends IMvpView, P extends IMvpPresenter<V>> {

    //主要代理这两个生命周期，用于绑定和解绑视图
    public void onCreate(Bundle savedInstanceState);

    public void onDestroy();

    //代理其余的生命周期

    public void onRestart();

    public void onStart();

    public void onResume();

    public void onPause();

    public void onStop();

    public void onAttachedToWindow();

    public void onContentChanged();

    public void onSaveInstanceState(Bundle outState);
}

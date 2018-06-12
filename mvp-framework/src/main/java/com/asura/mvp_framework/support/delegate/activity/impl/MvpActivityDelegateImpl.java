package com.asura.mvp_framework.support.delegate.activity.impl;

import android.os.Bundle;

import com.asura.mvp_framework.base.presenter.IMvpPresenter;
import com.asura.mvp_framework.base.view.IMvpView;
import com.asura.mvp_framework.support.delegate.MvpDelegateCallbackProxy;
import com.asura.mvp_framework.support.delegate.activity.MvpActivityDelegate;
import com.asura.mvp_framework.support.delegate.activity.MvpActivityDelegateCallBack;

/**
 * 静态代理：具体的目标接口实现类 该实现类对应的代理类是Activity
 *
 * @author Created by Asura on 2018/6/11 10:42.
 */
public class MvpActivityDelegateImpl<V extends IMvpView, P extends IMvpPresenter<V>>
        implements MvpActivityDelegate<V, P> {

    /**
     * 具体的目标接口实现类，需要持有创建Mvp实例
     */
    private MvpActivityDelegateCallBack<V, P> mMvpActivityDelegateCallBack;

    private MvpDelegateCallbackProxy<V, P> mMvpDelegateCallbackProxy;

    public MvpActivityDelegateImpl(MvpActivityDelegateCallBack<V, P> mvpActivityDelegateCallBack) {
        if (mvpActivityDelegateCallBack == null) {
            throw new NullPointerException("MvpActivityDelegateCallBack can't be null !");
        }
        this.mMvpActivityDelegateCallBack = mvpActivityDelegateCallBack;
    }

    private MvpDelegateCallbackProxy<V, P> getMvpDelegateCallbackProxy() {
        if (mMvpDelegateCallbackProxy == null) {
            mMvpDelegateCallbackProxy = new MvpDelegateCallbackProxy<>(mMvpActivityDelegateCallBack);
        }
        return mMvpDelegateCallbackProxy;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getMvpDelegateCallbackProxy().createPresenter();
        getMvpDelegateCallbackProxy().attachView();
    }

    @Override
    public void onDestroy() {
        getMvpDelegateCallbackProxy().detachView();
    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onAttachedToWindow() {

    }

    @Override
    public void onContentChanged() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}

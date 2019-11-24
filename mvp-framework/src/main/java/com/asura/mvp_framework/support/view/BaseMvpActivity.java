package com.asura.mvp_framework.support.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.asura.mvp_framework.base.presenter.IMvpPresenter;
import com.asura.mvp_framework.base.view.IMvpView;
import com.asura.mvp_framework.support.delegate.activity.MvpActivityDelegate;
import com.asura.mvp_framework.support.delegate.activity.MvpActivityDelegateCallBack;
import com.asura.mvp_framework.support.delegate.activity.impl.MvpActivityDelegateImpl;

/**
 * @author Created by Asura on 2018/6/11 10:54.
 */
public abstract class BaseMvpActivity<V extends IMvpView, P extends IMvpPresenter<V>> extends AppCompatActivity
        implements MvpActivityDelegateCallBack<V, P>, IMvpView {
    private P mPresenter;
    private MvpActivityDelegate<V, P> mMvpActivityDelegate;

    private MvpActivityDelegate<V, P> getMvpActivityDelegate() {
        if (mMvpActivityDelegate == null) {
            mMvpActivityDelegate = new MvpActivityDelegateImpl<>(this);
        }
        return mMvpActivityDelegate;
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.mPresenter = presenter;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V getMvpView() {
        return (V) this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpActivityDelegate().onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMvpActivityDelegate().onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMvpActivityDelegate().onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMvpActivityDelegate().onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpActivityDelegate().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getMvpActivityDelegate().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getMvpActivityDelegate().onStop();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getMvpActivityDelegate().onAttachedToWindow();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        getMvpActivityDelegate().onContentChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpActivityDelegate().onSaveInstanceState(outState);
    }
}

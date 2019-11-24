package com.asura.mvp_framework.support.delegate.fragment.impl;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asura.mvp_framework.base.presenter.IMvpPresenter;
import com.asura.mvp_framework.base.view.IMvpView;
import com.asura.mvp_framework.support.delegate.MvpDelegateCallbackProxy;
import com.asura.mvp_framework.support.delegate.fragment.MvpFragmentDelegate;
import com.asura.mvp_framework.support.delegate.fragment.MvpFragmentDelegateCallBack;

/**
 * @author Created by Asura on 2018/6/11 11:11.
 */
public class MvpFragmentDelegateImpl<V extends IMvpView, P extends IMvpPresenter<V>>
        implements MvpFragmentDelegate<V, P> {

    private MvpFragmentDelegateCallBack<V, P> mMvpFragmentDelegateCallBack;

    private MvpDelegateCallbackProxy<V, P> mMvpDelegateCallbackProxy;

    public MvpFragmentDelegateImpl(MvpFragmentDelegateCallBack<V, P> mvpFragmentDelegateCallBack) {
        if (mvpFragmentDelegateCallBack == null) {
            throw new NullPointerException("MvpFragmentDelegateCallBack can't be null !");
        }
        this.mMvpFragmentDelegateCallBack = mvpFragmentDelegateCallBack;
    }

    private MvpDelegateCallbackProxy<V, P> getMvpDelegateCallbackProxy() {
        if (mMvpDelegateCallbackProxy == null) {
            mMvpDelegateCallbackProxy = new MvpDelegateCallbackProxy<>(mMvpFragmentDelegateCallBack);
        }
        return mMvpDelegateCallbackProxy;
    }

    @Override
    public void onAttach(Context context) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getMvpDelegateCallbackProxy().createPresenter();
        getMvpDelegateCallbackProxy().attachView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

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
    public void onDestroyView() {
        getMvpDelegateCallbackProxy().detachView();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}

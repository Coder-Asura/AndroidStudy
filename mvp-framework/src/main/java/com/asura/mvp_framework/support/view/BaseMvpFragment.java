package com.asura.mvp_framework.support.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asura.mvp_framework.base.presenter.IMvpPresenter;
import com.asura.mvp_framework.base.view.IMvpView;
import com.asura.mvp_framework.support.delegate.fragment.MvpFragmentDelegate;
import com.asura.mvp_framework.support.delegate.fragment.MvpFragmentDelegateCallBack;
import com.asura.mvp_framework.support.delegate.fragment.impl.MvpFragmentDelegateImpl;

/**
 * @author Created by Asura on 2018/6/11 11:17.
 */
public abstract class BaseMvpFragment<V extends IMvpView, P extends IMvpPresenter<V>> extends Fragment
        implements MvpFragmentDelegateCallBack<V, P>, IMvpView {

    private P mPresenter;
    private MvpFragmentDelegate<V, P> mMvpFragmentDelegate;

    private MvpFragmentDelegate<V, P> getMvpFragmentDelegate() {
        if (mMvpFragmentDelegate == null) {
            mMvpFragmentDelegate = new MvpFragmentDelegateImpl<>(this);
        }
        return mMvpFragmentDelegate;
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
    public void onAttach(Context context) {
        super.onAttach(context);
        getMvpFragmentDelegate().onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpFragmentDelegate().onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getMvpFragmentDelegate().onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMvpFragmentDelegate().onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMvpFragmentDelegate().onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getMvpFragmentDelegate().onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        getMvpFragmentDelegate().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        getMvpFragmentDelegate().onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        getMvpFragmentDelegate().onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getMvpFragmentDelegate().onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getMvpFragmentDelegate().onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getMvpFragmentDelegate().onDetach();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpFragmentDelegate().onSaveInstanceState(outState);
    }
}

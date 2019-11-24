package com.asura.mvp_framework.support.delegate.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asura.mvp_framework.base.presenter.IMvpPresenter;
import com.asura.mvp_framework.base.view.IMvpView;

/**
 * 静态代理：定义 Fragmnet 目标接口
 * <p>
 * 代理生命周期
 *
 * @author Created by Asura on 2018/6/11 11:05.
 */
public interface MvpFragmentDelegate<V extends IMvpView, P extends IMvpPresenter<V>> {

    public void onAttach(Context context);

    public void onCreate(@Nullable Bundle savedInstanceState);

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState);

    public void onActivityCreated(@Nullable Bundle savedInstanceState);

    public void onStart();

    public void onResume();

    public void onPause();

    public void onStop();

    public void onDestroyView();

    public void onDestroy();

    public void onDetach();

    public void onSaveInstanceState(Bundle outState);
}

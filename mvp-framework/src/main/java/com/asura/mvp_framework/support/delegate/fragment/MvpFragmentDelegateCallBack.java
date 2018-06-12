package com.asura.mvp_framework.support.delegate.fragment;

import com.asura.mvp_framework.base.presenter.IMvpPresenter;
import com.asura.mvp_framework.base.view.IMvpView;
import com.asura.mvp_framework.support.delegate.MvpDelegateCallback;

/**
 * @author Created by Asura on 2018/6/11 10:47.
 */
public interface MvpFragmentDelegateCallBack<V extends IMvpView, P extends IMvpPresenter<V>>
        extends MvpDelegateCallback<V, P> {
}

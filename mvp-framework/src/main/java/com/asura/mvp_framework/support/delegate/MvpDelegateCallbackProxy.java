package com.asura.mvp_framework.support.delegate;

import com.asura.mvp_framework.base.presenter.IMvpPresenter;
import com.asura.mvp_framework.base.view.IMvpView;

/**
 * 具体的代理对象
 *
 * @author Created by Asura on 2018/6/11 10:06.
 */
public class MvpDelegateCallbackProxy<V extends IMvpView, P extends IMvpPresenter<V>>
        implements MvpDelegateCallback<V, P> {
    private MvpDelegateCallback<V, P> mMvpDelegateCallback;

    public MvpDelegateCallbackProxy(MvpDelegateCallback<V, P> callback) {
        this.mMvpDelegateCallback = callback;
    }

    @Override
    public P createPresenter() {
        P presenter = mMvpDelegateCallback.getPresenter();
        //为空就调用创建方法
        if (presenter == null) {
            presenter = mMvpDelegateCallback.createPresenter();
        }
        //还是为空，抛异常
        if (presenter == null) {
            throw new NullPointerException("Presenter can't be null ! Please check createPresenter() method .");
        }
        mMvpDelegateCallback.setPresenter(presenter);
        return presenter;
    }

    @Override
    public P getPresenter() {
        P presenter = mMvpDelegateCallback.getPresenter();
        //为空就抛异常
        if (presenter == null) {
            throw new NullPointerException("Presenter can't be null ! Please check createPresenter() method .");
        }
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        mMvpDelegateCallback.setPresenter(presenter);
    }

    @Override
    public V getMvpView() {
        return mMvpDelegateCallback.getMvpView();
    }

    public void attachView() {
        getPresenter().attachView(getMvpView());
    }

    public void detachView() {
        getPresenter().detachView();
    }
}

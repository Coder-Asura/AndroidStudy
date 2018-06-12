package com.asura.mvp_framework.base.presenter.impl;

import com.asura.mvp_framework.base.presenter.IMvpPresenter;
import com.asura.mvp_framework.base.view.IMvpView;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 统一管理 V 层视图的绑定和解绑
 *
 * @author Created by Asura on 2018/6/11 9:29.
 */
public class BaseMvpPresenter<V extends IMvpView> implements IMvpPresenter<V> {
    private WeakReference<V> mWeakReferenceView;
    /**
     * 代理 View
     */
    private V mProxyView;

    /**
     * @return View 视图
     */
    public V getView() {
        return mProxyView;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void attachView(V view) {
        mWeakReferenceView = new WeakReference<V>(view);
        // 动态代理
        mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(),
                view.getClass().getInterfaces(),
                new MvpViewInvocationHandler(mWeakReferenceView.get()));
    }

    @Override
    public void detachView() {
        if (this.mWeakReferenceView != null) {
            this.mWeakReferenceView.clear();
            this.mWeakReferenceView = null;
        }
    }

    /**
     * 检查 View 是否不为空
     *
     * @return View 是否不为空
     */
    private boolean hasAttachView() {
        return this.mWeakReferenceView != null
                && this.mWeakReferenceView.get() != null;
    }

    private class MvpViewInvocationHandler implements InvocationHandler {
        private IMvpView mMvpView;

        public MvpViewInvocationHandler(IMvpView view) {
            this.mMvpView = view;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (hasAttachView()) {
                return method.invoke(mMvpView, args);
            }
            return null;
        }
    }
}

package com.asura.mvp_framework.support.delegate;

import com.asura.mvp_framework.base.presenter.IMvpPresenter;
import com.asura.mvp_framework.base.view.IMvpView;

/**
 * 静态代理:目标接口
 *
 * @author Created by Asura on 2018/6/11 10:01.
 */
public interface MvpDelegateCallback<V extends IMvpView, P extends IMvpPresenter<V>> {
    /**
     * 创建 Presenter 实例
     *
     * @return P
     */
    public P createPresenter();

    /**
     * 获取 Presenter 实例
     *
     * @return P
     */
    public P getPresenter();

    /**
     * 设置 Presenter 实例
     *
     * @param presenter P
     */
    public void setPresenter(P presenter);

    /**
     * 获取具体的 View 实例
     *
     * @return V
     */
    public V getMvpView();

}

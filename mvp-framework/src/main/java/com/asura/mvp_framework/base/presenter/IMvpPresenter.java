package com.asura.mvp_framework.base.presenter;

import com.asura.mvp_framework.base.view.IMvpView;

/**
 * P 层接口
 *
 * @author Created by Asura on 2018/6/11 9:23.
 */
public interface IMvpPresenter<V extends IMvpView> {
    /**
     * 绑定视图
     *
     * @param view V 层视图
     */
    void attachView(V view);

    /**
     * 解绑视图
     */
    void detachView();
}

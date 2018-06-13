package com.asura.jsoup_study.ui.main

import com.asura.mvp_framework.base.view.IMvpView

/**
 * @author Created by Asura on 2018/6/7 15:49.
 */
interface IMainView : IMvpView {
    /**
     * 切换内容
     */
    fun switchContent(position: Int);

}
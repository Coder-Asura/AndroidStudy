package com.asura.jsoup_demo.ui.main

import com.asura.jsoup_demo.mvp.IView

/**
 * @author Created by Asura on 2018/6/7 15:49.
 */
interface IMainView : IView {
    /**
     * 切换内容
     */
    fun switchContent(position: Int);

}
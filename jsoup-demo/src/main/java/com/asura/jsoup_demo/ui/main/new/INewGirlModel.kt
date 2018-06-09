package com.asura.jsoup_demo.ui.main.new

import com.asura.jsoup_demo.mvp.ui.lce.ILCEModel

/**
 * @author Created by Asura on 2018/6/8 14:01.
 */
interface INewGirlModel<T> : ILCEModel<T> {

    fun parseHtml2List(html: String): T

}
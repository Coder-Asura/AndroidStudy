package com.asura.jsoup_demo.ui.main.beautiful

import com.asura.jsoup_demo.bean.BeautifulGirl
import com.asura.jsoup_demo.mvp.ui.lce.ILCEModel

/**
 * @author Created by Asura on 2018/6/8 14:01.
 */
interface IBeautifulGirlModel : ILCEModel {

    fun parseHtml2List(html: String):List<BeautifulGirl>

}
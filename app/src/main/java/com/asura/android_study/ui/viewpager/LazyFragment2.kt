package com.asura.android_study.ui.viewpager

import android.os.Bundle

import com.asura.android_study.R
import com.asura.android_study.ui.base.BaseLazyFragment
import kotlinx.android.synthetic.main.fragment_lazy1.*

/**
 * Created by Liuxd on 2016/11/12 15:50.
 */
class LazyFragment2 : BaseLazyFragment() {


    protected override fun initPrepare() {
        tv.text = "22222"
        tv_name.text = arguments?.getString("text")
    }

    protected override fun initDataReal() {}
    override fun setLayoutId(): Int {
        return R.layout.fragment_lazy1
    }

    override fun initView() {

    }

    fun setTv_name(name: String?) {
        tv_name.text = name
    }

    companion object {
        @JvmStatic
        fun create(text: String?): LazyFragment2 {
            val fragment = LazyFragment2()
            val bundle = Bundle()
            bundle.putString("text", text)
            fragment.arguments = bundle
            return fragment
        }
    }
}
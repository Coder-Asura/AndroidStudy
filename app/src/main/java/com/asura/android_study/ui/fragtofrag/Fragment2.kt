package com.asura.android_study.ui.fragtofrag

import android.content.Context
import android.os.Bundle
import com.asura.android_study.R
import com.asura.android_study.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment2.*

/**
 * Created by Liuxd on 2016/11/1 21:05.
 */
class Fragment2 : BaseFragment() {
    var mMyListener: MyListener? = null


    override fun onAttachToContext(context: Context?) {
        mMyListener = context as MyListener?
    }


    override fun setLayoutId(): Int {
        return R.layout.fragment2
    }

    override fun initView() {
        if (arguments != null) {
            textView2.text = requireArguments().getString("name", "default")
        }
        button2.setOnClickListener {
            mMyListener?.sendFrom2("收到fragment2的值")
        }
    }

    companion object {
        @JvmStatic
        fun createInstance(name: String?): Fragment2 {
            val fragment2 = Fragment2()
            val bundle = Bundle()
            bundle.putString("name", name)
            fragment2.arguments = bundle
            return fragment2
        }
    }
}
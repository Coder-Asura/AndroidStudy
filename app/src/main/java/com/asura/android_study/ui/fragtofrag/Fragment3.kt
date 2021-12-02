package com.asura.android_study.ui.fragtofrag

import android.content.Context
import android.os.Bundle
import com.asura.android_study.R
import com.asura.android_study.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment3.*

/**
 * Created by Liuxd on 2016/11/1 21:05.
 */
class Fragment3 : BaseFragment() {
    var mMyListener: MyListener? = null


    override fun onAttachToContext(context: Context?) {
        mMyListener = context as MyListener?
    }


    override fun setLayoutId(): Int {
        return R.layout.fragment3
    }

    override fun initView() {
        if (arguments != null) {
            textView3.text = requireArguments().getString("name", "default")
        }
        button3.setOnClickListener {
            mMyListener?.sendFrom2("收到fragment3的值")
        }
    }

    companion object {
        @JvmStatic
        fun createInstance(name: String?): Fragment3 {
            val fragment3 = Fragment3()
            val bundle = Bundle()
            bundle.putString("name", name)
            fragment3.arguments = bundle
            return fragment3
        }
    }
}
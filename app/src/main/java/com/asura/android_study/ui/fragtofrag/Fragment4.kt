package com.asura.android_study.ui.fragtofrag

import android.content.Context
import android.os.Bundle
import com.asura.android_study.R
import com.asura.android_study.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment4.*

/**
 * Created by Liuxd on 2016/11/1 21:05.
 */
class Fragment4 : BaseFragment() {
    var mMyListener: MyListener? = null


    override fun onAttachToContext(context: Context?) {
        mMyListener = context as MyListener?
    }


    override fun setLayoutId(): Int {
        return R.layout.fragment4
    }

    override fun initView() {
        if (arguments != null) {
            textView4.text = requireArguments().getString("name", "default")
        }
        button4.setOnClickListener {
            mMyListener?.sendFrom2("收到fragment4的值")
        }
    }

    companion object {
        @JvmStatic
        fun createInstance(name: String?): Fragment4 {
            val fragment4 = Fragment4()
            val bundle = Bundle()
            bundle.putString("name", name)
            fragment4.arguments = bundle
            return fragment4
        }
    }
}
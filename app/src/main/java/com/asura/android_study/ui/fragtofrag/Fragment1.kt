package com.asura.android_study.ui.fragtofrag

import android.content.Context
import com.asura.android_study.R
import com.asura.android_study.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment1.*

/**
 * Created by Liuxd on 2016/11/1 21:05.
 */
class Fragment1 : BaseFragment() {
    private var listener: MyListener? = null

    override fun setLayoutId(): Int {
        return R.layout.fragment1
    }

    override fun initView() {
        button1.setOnClickListener {
            listener?.sendFrom1("收到fragment1的值")
        }
    }

    override fun onAttachToContext(context: Context?) {
        super.onAttachToContext(context)
        listener = context as? MyListener
    }

    fun setTextView1(info: String?) {
        textView1.text = info
    }

    companion object {
        @JvmStatic
        fun createInstance(): Fragment1 {
            return Fragment1()
        }
    }
}
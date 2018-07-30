package com.asura.android_study.activity.eventbus

import android.view.View
import com.asura.a_log.ALog
import com.asura.android_study.R
import com.asura.android_study.activity.base.BaseActivity
import com.asura.android_study.activity.eventbus.event.MessageEvent
import kotlinx.android.synthetic.main.activity_publish.*
import org.greenrobot.eventbus.EventBus
import kotlin.concurrent.thread

/**
 * @author Created by Asura on 2018/7/20 15:57.
 * 发布事件页面
 */
class PublishActivity : BaseActivity(), View.OnClickListener {
    override fun setLayoutId(): Int {
        return R.layout.activity_publish;
    }

    override fun initView() {
        btn_main_publish.setOnClickListener(this)
        btn_thread_publish.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_main_publish -> {
                val msg = "来自主线程的消息:" + Thread.currentThread().name
                EventBus.getDefault().post(MessageEvent(msg))
                ALog.d("主线程发送消息:$msg")
            }
            btn_thread_publish -> {
                val msg = "来自子线程的消息:" + Thread.currentThread().name
                thread {
                    EventBus.getDefault().post(MessageEvent(msg))
                }
                ALog.d("子线程发送消息:$msg")
            }
        }
    }


}
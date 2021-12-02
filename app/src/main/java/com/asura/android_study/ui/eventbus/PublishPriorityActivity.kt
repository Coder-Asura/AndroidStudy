package com.asura.android_study.ui.eventbus

import android.view.View
import com.asura.a_log.ALog
import com.asura.android_study.R
import com.asura.android_study.ui.base.BaseActivity
import com.asura.android_study.ui.eventbus.event.PriorityEvent
import kotlinx.android.synthetic.main.activity_subscribe_priority.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * @author Created by Asura on 2018/7/23 16:23.
 * 设置订阅优先级页面
 */
class PublishPriorityActivity : BaseActivity(), View.OnClickListener {
    override fun setLayoutId(): Int {
        return R.layout.activity_subscribe_priority;
    }

    override fun initView() {
        btn_publish_priority.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        EventBus.getDefault().post(PriorityEvent("这是来自主线程的消息"))
    }

    //优先级，数字越大越优先收到
    @Subscribe(priority = 1)
    fun onMsgEvent1(event: PriorityEvent) {
        tv_msg_priority_1.text = event.msg
        ALog.d("onMsgEvent1:${event.msg}")
    }

    @Subscribe(priority = 10)
    fun onMsgEvent10(event: PriorityEvent) {
        tv_msg_priority_2.text = event.msg
        ALog.d("onMsgEvent10:${event.msg}")
    }

    @Subscribe(priority = 100)
    fun onMsgEvent100(event: PriorityEvent) {
        tv_msg_priority_3.text = "${event.msg},并拦截事件"
        ALog.d("onMsgEvent100:${event.msg}")

        //这里可以拦截掉消息
        EventBus.getDefault().cancelEventDelivery(event)
        ALog.d("拦截事件，cancelEventDelivery:${event.msg}")
    }

    @Subscribe(priority = 1000)
    fun onMsgEvent1000(event: PriorityEvent) {
        tv_msg_priority_4.text = event.msg
        ALog.d("onMsgEvent1000:${event.msg}")
    }

    @Subscribe(priority = 10000)
    fun onMsgEvent10000(event: PriorityEvent) {
        tv_msg_priority_5.text = event.msg
        ALog.d("onMsgEvent10000:${event.msg}")
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }
}
package com.asura.android_study.ui.eventbus

import android.view.View
import com.asura.a_log.ALog
import com.asura.android_study.R
import com.asura.android_study.ui.base.BaseActivity
import com.asura.android_study.ui.eventbus.event.StickyEvent
import kotlinx.android.synthetic.main.activity_sticky.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author Created by Asura on 2018/7/23 15:51.
 * 接收粘性事件页面
 */
class StickyActivity : BaseActivity(), View.OnClickListener {
    override fun setLayoutId(): Int {
        return R.layout.activity_sticky;
    }

    override fun initView() {
        btn_remove_sticky.setOnClickListener(this)
        btn_register_sticky.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_remove_sticky -> {
                /*//这个方法内部有一个强转操作,在不存在粘性事件时会抛异常，不采用
                val event: StickyEvent = EventBus.getDefault().getStickyEvent(StickyEvent::class.java)
                if (event != null) {
                    //这里可以移除黏性事件
                    EventBus.getDefault().removeStickyEvent(event)
                    ALog.d("移除黏性事件:${event.msg}")
                }*/
                val event = EventBus.getDefault().removeStickyEvent(StickyEvent::class.java)
                ALog.d("移除黏性事件")
                if (event != null) {
                    ALog.d("移除黏性事件:${event.msg}")
                }
            }
            btn_register_sticky -> {
                EventBus.getDefault().register(this)
                ALog.d("订阅黏性事件")
            }
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onStickEvent(event: StickyEvent) {
        tv_sticky_msg.text = event.msg
        ALog.d("onStickEvent:${event.msg}")
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

}
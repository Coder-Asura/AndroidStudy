package com.asura.android_study.activity.eventbus

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.view.View
import com.asura.a_log.ALog
import com.asura.android_study.R
import com.asura.android_study.activity.base.BaseActivity
import com.asura.android_study.activity.eventbus.event.MessageEvent
import com.asura.android_study.activity.eventbus.event.NetStateEvent
import com.asura.android_study.activity.eventbus.event.StickyEvent
import kotlinx.android.synthetic.main.activity_subscribe.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author Created by Asura on 2018/7/20 15:50.
 * 接收事件页面
 */
class SubscribeActivity : BaseActivity(), View.OnClickListener {

    var netWorkReceiver: NetWorkReceiver? = null

    override fun setLayoutId(): Int {
        return R.layout.activity_subscribe;
    }

    override fun initView() {
        btn_to_publish.setOnClickListener(this)
        btn_publish_sticky.setOnClickListener(this)
        btn_to_sticky.setOnClickListener(this)
        btn_to_priority.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_to_publish -> {
                startActivity(Intent(this@SubscribeActivity, PublishActivity::class.java))
            }
            btn_publish_sticky -> {
                val msg = "这是黏性事件:" + Thread.currentThread().name
                //发送粘性事件
                EventBus.getDefault().postSticky(StickyEvent(msg))
                ALog.d("发送粘性事件:$msg")
            }
            btn_to_sticky -> {
                startActivity(Intent(this@SubscribeActivity, StickyActivity::class.java))
            }
            btn_to_priority -> {
                startActivity(Intent(this@SubscribeActivity, PublishPriorityActivity::class.java))
            }
        }

    }

    //默认线程模式，与发送者同一线程，开销小，效率高无需线程切换，应快速返回避免阻塞
    @Subscribe(threadMode = ThreadMode.POSTING)
    fun onPostingMsgEvent(event: MessageEvent) {
        ALog.d("onPostingMsgEvent:" + event.msg)
    }

    //主线程模式（通常是UI线程），应快速返回避免阻塞
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMainMsgEvent(event: MessageEvent) {
        tv_msg.visibility = View.VISIBLE
        tv_msg.text = event.msg
        ALog.d("onMainMsgEvent:" + event.msg)
    }

    //主线程模式（通常是UI线程），应快速返回避免阻塞，
    // 不过需要排队，如果前一个也是main_ordered 需要等前一个执行完成后才执行
    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    fun onMainOrderedMsgEvent(event: MessageEvent) {
        tv_msg.visibility = View.VISIBLE
        tv_msg.text = event.msg
        ALog.d("onMainOrderedMsgEvent:" + event.msg)
    }

    //后台进程，处理如保存到数据库等操作
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onThreadMsgEvent(event: MessageEvent) {
        ALog.d("onThreadMsgEvent:" + event.msg)
    }

    //异步执行，另起线程操作
    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onAsyncMsgEvent(event: MessageEvent) {
        ALog.d("onAsyncMsgEvent:" + event.msg)
    }

    //和广播通信
    @Subscribe(threadMode = ThreadMode.POSTING)
    fun onNetWorkChanged(event: NetStateEvent) {
        ALog.d("onNetWorkChanged:${event.state}")
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        netWorkReceiver = NetWorkReceiver();
        registerReceiver(netWorkReceiver, intentFilter)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(netWorkReceiver)
    }
}
package com.asura.android_study.activity.eventbus

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo

import com.asura.a_log.ALog
import com.asura.android_study.activity.eventbus.event.NetStateEvent
import org.greenrobot.eventbus.EventBus

/**
 * Created by Liuxd on 2018/7/24 18:04.
 * 网络广播监听者
 */
//class NetWorkReceiver(private val mCallback: INetworkStateChangeCallback) : BroadcastReceiver() {
class NetWorkReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != null && intent.action == ConnectivityManager.CONNECTIVITY_ACTION) {
            val mConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var netInfo: NetworkInfo? = null
            if (mConnectivityManager != null) {
                netInfo = mConnectivityManager.activeNetworkInfo
            }
            if (netInfo != null && netInfo.isConnected) {
                ALog.d("onReceive :网络可用")
//                mCallback.onNetworkAvailable(true)
                EventBus.getDefault().post(NetStateEvent(NetState.NET_AVAILABLE))
            } else {
                ALog.d("onReceive :网络断开")
//                mCallback.onNetworkAvailable(false)
                EventBus.getDefault().post(NetStateEvent(NetState.NET_NULL))
            }
        }
    }

//    interface INetworkStateChangeCallback {
//        /**
//         * 判断网络是否可用
//         *
//         * @param available 网络是否可用
//         */
//        fun onNetworkAvailable(available: Boolean)
//    }
}

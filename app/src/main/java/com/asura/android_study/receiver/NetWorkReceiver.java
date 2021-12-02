package com.asura.android_study.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.asura.a_log.ALog;

/**
 * Created by Liuxd on 2016/9/6 10:04.
 * 网络广播监听者
 */
public class NetWorkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = mConnectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                ALog.INSTANCE.e("onReceive", "网络可用" + netInfo.getType() + "    ,getTypeName==" + netInfo.getTypeName()
                        + "getSubtype==" + netInfo.getSubtype());
                //网络可用时同步数据
                //                if (netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                //                    //WiFi网络
                //                } else if (netInfo.getType() == ConnectivityManager.TYPE_ETHERNET) {
                //                    //有线网络
                //                } else if (netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                //                    //3g网络
                //                } else if (netInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_LTE) {
                //                    //4G网络
                //                }
            } else {
                //网络断开
                ALog.INSTANCE.e("onReceive", "网络断开");
            }
        }
    }

    ;

}

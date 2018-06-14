package com.liuxd.baseapplibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * Created by Liuxd on 2016/10/27 16:08.
 * 手机设备工具类
 */

public class DeviceUtil {
    /**
     * 获取IMEI号
     */
    @SuppressLint("MissingPermission")
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    /**
     * @return 设备名称及sdk版本号
     */
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        int sdk_int = Build.VERSION.SDK_INT;
        if (model.startsWith(manufacturer)) {
            return model + "_" + sdk_int;
        } else {
            return manufacturer + "_" + model + "_" + sdk_int;
        }
    }
}

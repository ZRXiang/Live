package com.zrx.live.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.zrx.live.InkeApp;


public class Utilitys {

    public static Long TIME = TimeUtil.getTimeDate();

    /**
     * 获取application中指定的meta-data
     *
     * @return 如果没有获取成功(没有对应值, 或者异常)，则返回值为空
     */
    public static String getChannel() {
        String resultData = "android-360";
        try {
            PackageManager packageManager = InkeApp.getContext().getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(InkeApp.getContext().getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString("UMENG_CHANNEL");
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return resultData;
    }
}


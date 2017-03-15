package com.zrx.live.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.zrx.live.InkeApp;
import com.zrx.live.bean.CookieInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class CookieUtil {

    public static String createCookie() {
        Context context = InkeApp.getContext();
        long first_time = PreferenceUtils.getPrefLong(context, "first_time", 0);
        String uuid = PreferenceUtils.getPrefString(context, "cookie_id", getUUID());
        if (first_time == 0) {
            first_time = System.currentTimeMillis();
            PreferenceUtils.setPrefString(context, "cookie_id", uuid);
        } else {
            long days = (System.currentTimeMillis() - first_time) / (1000 * 60 * 60 * 24);
            if (days > 30) {
                first_time = System.currentTimeMillis();
                uuid = getUUID();
                PreferenceUtils.setPrefString(context, "cookie_id", uuid);
            }
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date time = new Date(first_time);
        String channel = Utilitys.getChannel();
        String versionInfo = "YuYouBang_android_v" + VersionUtils.getVersionName(context);
        CookieInfo cookieInfo = new CookieInfo(uuid, df.format(time), channel, versionInfo);
        String s = new Gson().toJson(cookieInfo, CookieInfo.class);
        return "cpsInfo=" + s;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString();
    }
}

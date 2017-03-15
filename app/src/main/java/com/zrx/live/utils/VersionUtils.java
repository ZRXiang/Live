package com.zrx.live.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 获取版本和版本号的工具类
 */
public class VersionUtils {
//	getAppVersion
	public static String getVersionInfo(Context context) {
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo packinfo = pm.getPackageInfo(context.getPackageName(), 0);
			String pack = packinfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
			return pack + "V" + packinfo.versionName;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
//	getAppCurrentVersion
	public static String getVersionName(Context context) {
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo packinfo = pm.getPackageInfo(context.getPackageName(), 0);
			return  packinfo.versionName;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
//	getAppVersionCode
	public static int getVersionCode(Context context) {
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo packinfo = pm.getPackageInfo(context.getPackageName(), 0);
			return packinfo.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}

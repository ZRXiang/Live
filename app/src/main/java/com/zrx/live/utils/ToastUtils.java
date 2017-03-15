package com.zrx.live.utils;

import android.widget.Toast;


public class ToastUtils {
    private static Toast mToast = null;

    private ToastUtils() {
    }

    public static void showShort(int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(ContextUtil.getInstance(), resId, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(resId);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showShort(String message) {
        if (mToast == null) {
            mToast = Toast.makeText(ContextUtil.getInstance(), message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showLong(int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(ContextUtil.getInstance(), resId, Toast.LENGTH_LONG);
        } else {
            mToast.setText(resId);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
    }

    public static void showLong(String message) {
        if (mToast == null) {
            mToast = Toast.makeText(ContextUtil.getInstance(), message, Toast.LENGTH_LONG);
        } else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
    }
}

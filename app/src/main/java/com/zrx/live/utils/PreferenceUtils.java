package com.zrx.live.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.zrx.live.InkeApp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.List;

public class PreferenceUtils {
    private static final String PREFERENCES_NAME = "yuyoubang_store";


    public static void saveString(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(String key) {
        return getSharedPreferences().getString(key, null);
    }

    static SharedPreferences getSharedPreferences() {
        return InkeApp.getContext().getSharedPreferences("config_yuyoubang", Context.MODE_PRIVATE);
    }

    public static void putsessionId(String value) {
        SharedPreferences sp = InkeApp.getContext().getSharedPreferences(
                "config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("sessionid", value);
        edit.commit();
    }

    public static String getsessionId(Context context) {
        SharedPreferences sp = InkeApp.getContext().getSharedPreferences(
                "config", Context.MODE_PRIVATE);
        return sp.getString("sessionid", "");
    }

    public static String getPrefString(Context context, String key, final String defaultValue) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getString(key, defaultValue);
    }

    public static void setPrefString(Context context, final String key, final String value) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings.edit().putString(key, value).commit();
    }

    public static boolean getPrefBoolean(Context context, final String key,
                                         final boolean defaultValue) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getBoolean(key, defaultValue);
    }

    public static boolean hasKey(Context context, final String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).contains(key);
    }

    public static void setPrefBoolean(Context context, final String key, final boolean value) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings.edit().putBoolean(key, value).commit();
    }

    public static void setPrefInt(Context context, final String key, final int value) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings.edit().putInt(key, value).commit();
    }

    public static int getPrefInt(Context context, final String key, final int defaultValue) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getInt(key, defaultValue);
    }

    public static void setPrefFloat(Context context, final String key, final float value) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings.edit().putFloat(key, value).commit();
    }

    public static <T> void setPrefObject(Context context, final String key, final Class<T> clazz) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings.edit().putString(key, new Gson().toJson(clazz)).commit();
    }


    public static float getPrefFloat(Context context, final String key, final float defaultValue) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getFloat(key, defaultValue);
    }

    public static void setSettingLong(Context context, final String key, final long value) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings.edit().putLong(key, value).commit();
    }

    public static long getPrefLong(Context context, final String key, final long defaultValue) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getLong(key, defaultValue);
    }


    public static void clearPreference(Context context) {
        final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.clear();
        editor.commit();
    }

    public static boolean isEmpty(String key) {
        return TextUtils.isEmpty(PreferenceUtils.getPrefString(InkeApp.getContext(), key, ""));
    }

    public static <T> T getClaz(String key, Type type) {
        try {
            String prefString = PreferenceUtils.getPrefString(InkeApp.getContext(), key, "");
            return new Gson().fromJson(prefString, type);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    public static <T> T getObject(String key, Class<T> clazz) {
        try {
            String prefString = PreferenceUtils.getPrefString(InkeApp.getContext(), key, "");
            return new Gson().fromJson(prefString, clazz);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }


    /**
     * 序列化对象
     *
     * @return
     * @throws IOException
     */
    public static String serialize(Object t) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                byteArrayOutputStream);
        objectOutputStream.writeObject(t);
        String serStr = byteArrayOutputStream.toString("ISO-8859-1");
        serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return serStr;
    }

    /**
     * 反序列化对象
     *
     * @param str
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> T deSerialization(String str) throws IOException,
            ClassNotFoundException {
        String redStr = java.net.URLDecoder.decode(str, "UTF-8");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                redStr.getBytes("ISO-8859-1"));
        ObjectInputStream objectInputStream = new ObjectInputStream(
                byteArrayInputStream);
        Object o = objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return (T) o;
    }


    public static void loadList(Context context, List<String> historyList) {
        SharedPreferences mSharedPreference1 = PreferenceManager.getDefaultSharedPreferences(context);
        historyList.clear();
        int size = mSharedPreference1.getInt("Status_size", 0);

        for (int i = 0; i < size; i++) {
            historyList.add(mSharedPreference1.getString("Status_" + i, null));
        }
    }

    public static boolean saveList(Context context, List<String> historyList) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor mEdit1 = sp.edit();
        mEdit1.putInt("Status_size", historyList.size()); /*sKey is an array*/

        for (int i = 0; i < historyList.size(); i++) {
            mEdit1.remove("Status_" + i);
            mEdit1.putString("Status_" + i, historyList.get(i));
        }

        return mEdit1.commit();
    }
}

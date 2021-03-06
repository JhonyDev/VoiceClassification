package com.app.voiceclassification.utils;

import android.app.Activity;
import android.content.SharedPreferences;

import com.app.voiceclassification.info.Info;


public class SharedPrefUtils {

    public static void putStringSharedPrefs(Activity context, String value, String key) {
        SharedPreferences mPrefs = context.getSharedPreferences("SharedPrefs", 0);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringSharedPrefs(Activity context, String key) {
        SharedPreferences mPrefs = context.getSharedPreferences("SharedPrefs", 0);
        return mPrefs.getString(key, "");
    }

    public static String getToken(Activity context) {
        return "Token " + SharedPrefUtils.getStringSharedPrefs(context, Info.TOKEN);
    }

    public static boolean isTokenEmpty(Activity context) {
        return SharedPrefUtils.getStringSharedPrefs(context, Info.TOKEN).isEmpty();
    }

    public static void setToken(Activity context, String token) {
        SharedPreferences mPrefs = context.getSharedPreferences("SharedPrefs", 0);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(Info.TOKEN, token);
        editor.apply();
    }
}

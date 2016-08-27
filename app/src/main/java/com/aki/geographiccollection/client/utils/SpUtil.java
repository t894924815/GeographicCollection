package com.aki.geographiccollection.client.utils;

import android.content.Context;
import android.content.SharedPreferences;


import com.aki.geographiccollection.client.GeoApplication;


public class SpUtil {
    private static final SpUtil instance = new SpUtil();
    private static SharedPreferences sharedPreferences = GeoApplication.application.getSharedPreferences("app", Context.MODE_APPEND);

    private SpUtil() {
    }

    public static SpUtil getInstance() {
        return instance;
    }

    public void put(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public boolean getBollean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public String get(String key) {
        return sharedPreferences.getString(key, "");
    }

    public static void clearData() {
        sharedPreferences.edit().clear().apply();
    }
}

package com.aki.geographiccollection.client.utils;

import android.text.TextUtils;
import android.widget.Toast;
import com.aki.geographiccollection.client.GeoApplication;

public class ToastUtil {
    private static final ToastUtil instance = new ToastUtil();
    private static Toast toast;

    private ToastUtil() {
    }

    public static ToastUtil getInstance() {
        return instance;
    }

    public static void showToast(String text) {
        if (!TextUtils.isEmpty(text)) {
            if (toast == null) {
                toast = Toast.makeText(GeoApplication.application, text, Toast.LENGTH_SHORT);
            } else {
                toast.setText(text);
            }
            toast.show();
        }

    }
}

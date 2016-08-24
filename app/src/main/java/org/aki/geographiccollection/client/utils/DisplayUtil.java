package org.aki.geographiccollection.client.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;


import org.aki.geographiccollection.client.GeoApplication;

public class DisplayUtil {

    public static int dip2px(float dpValue) {
        final float scale = GeoApplication.application.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int getScreanWidth() {
        WindowManager windowManager = (WindowManager) GeoApplication.application.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metric = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }

    public static int getScreanHeight() {
        WindowManager windowManager = (WindowManager) GeoApplication.application.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metric = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels;
    }

    public static int fromDPToPix(int dp) {
        return Math.round(AppUtils.getDensity() * dp);
    }


}

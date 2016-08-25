package org.aki.geographiccollection.client.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import org.aki.geographiccollection.R;
import org.aki.geographiccollection.client.GeoApplication;

/**
 * Created by aki on 2016/8/25.
 */
public class UIUtils {
    private static final UIUtils instance = new UIUtils();

    public static UIUtils getInstance() {
        return instance;
    }

    private UIUtils() {

    }

    public View getBaseDialogView(Window window) {
        return LayoutInflater.from(GeoApplication.application).inflate(R.layout.layout_dialog_base, (ViewGroup) window.getDecorView(), true);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T $(View parent, int resId) {
        return (T) parent.findViewById(resId);
    }
}

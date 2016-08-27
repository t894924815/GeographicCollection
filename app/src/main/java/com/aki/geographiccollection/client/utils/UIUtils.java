package com.aki.geographiccollection.client.utils;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.baidu.mapapi.map.MapView;

import com.aki.geographiccollection.client.GeoApplication;

import org.aki.geographiccollection.R;

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

    public void simulateClick(View view ,float x, float y){
        long downTime = SystemClock.uptimeMillis();
        MotionEvent downEvent = MotionEvent.obtain(downTime,downTime,MotionEvent.ACTION_DOWN,x,y,0);
        MotionEvent upEvent = MotionEvent.obtain(downTime,SystemClock.uptimeMillis(),MotionEvent.ACTION_UP,x,y,0);

        ((MapView) view).getChildAt(0).onTouchEvent(downEvent);
        ((MapView) view).getChildAt(0).onTouchEvent(upEvent);

        downEvent.recycle();
        upEvent.recycle();

    }

    public View getBaseDialogView(Window window) {
        return LayoutInflater.from(GeoApplication.application).inflate(R.layout.layout_dialog_base, (ViewGroup) window.getDecorView(), true);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T $(View parent, int resId) {
        return (T) parent.findViewById(resId);
    }
}

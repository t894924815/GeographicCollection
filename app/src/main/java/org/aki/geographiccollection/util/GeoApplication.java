package org.aki.geographiccollection.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;

import java.util.ArrayList;

/**
 * Created by wudi on 2016/8/24.
 */
public class GeoApplication extends Application implements Application.ActivityLifecycleCallbacks {
    public static ArrayMap<String, String> params;
    public static GeoApplication application;
    public ArrayList<Activity> activities;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        params = new ArrayMap<>();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activities.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activities.remove(activity);
    }
}

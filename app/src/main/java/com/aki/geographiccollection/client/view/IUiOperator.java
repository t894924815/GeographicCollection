package com.aki.geographiccollection.client.view;


import android.support.v4.app.Fragment;

/**
 * Created by aki on 2016/8/25.
 */
public interface IUiOperator {
    public void showMapType();

    public void hideMapType();

    public void updateTopData(String num);

    public void addFragment(Fragment fragment);
    public void showFragment(Fragment fragment);
    public void hideFragment(Fragment fragment);

    public void showAimTarget();

    public void hideAimTarget();

    public void moveAimTarget(int x, int y);

    void hideCollectView();
}

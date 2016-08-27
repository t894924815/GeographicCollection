package com.aki.geographiccollection.client.model;

import android.support.v4.util.ArrayMap;

import com.aki.geographiccollection.client.bean.ServerData;

import rx.functions.Action1;

/**
 * Created by aki on 2016/8/26.
 */
public interface INetWork {
    void validateLogin(String userName, String password,GetResultCallBack getResultCallBack);

    ServerData getMapData(String userId, GetResultCallBack getResultCallBack);

    void uploadPhoto(final Action1<ArrayMap<String, String>> ac, byte[] bytes, final String filename, boolean issmall);
}

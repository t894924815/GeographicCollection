package com.aki.geographiccollection.client.model;

import com.aki.geographiccollection.client.bean.ServerData;

/**
 * Created by aki on 2016/8/26.
 */
public interface INetWork {
    void validateLogin(String userName, String password,GetResultCallBack getResultCallBack);

    ServerData getMapData(String userId, GetResultCallBack getResultCallBack);
}

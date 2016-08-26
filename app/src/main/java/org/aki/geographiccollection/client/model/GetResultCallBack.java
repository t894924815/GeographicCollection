package org.aki.geographiccollection.client.model;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by aki on 2016/8/26.
 */
public interface GetResultCallBack {
    void success(JSONObject content);
    void failed(String message);
    void error(String errorCode);
}

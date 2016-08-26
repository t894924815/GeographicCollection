package org.aki.geographiccollection.client.model;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;

import org.aki.geographiccollection.client.GeoApplication;
import org.aki.geographiccollection.client.bean.ServerData;
//import org.aki.geographiccollection.client.utils.HttpUtils;

import rx.functions.Action1;


/**
 * Created by aki on 2016/8/26.
 */
public class NetWork implements INetWork {


    private final ArrayMap<String, String> params;

    public NetWork() {
        params = GeoApplication.params;
    }

    @Override
    public void validateLogin(String userName, String password, final GetResultCallBack getResultCallBack) {
        params.put("methodName", "userLogin");
        params.put("username", userName);
        params.put("password", password);
//        HttpUtils.http(params, new Action1<JSONObject>() {
//            @Override
//            public void call(JSONObject jsonObject) {
//                params.clear();
//                if (TextUtils.equals(jsonObject.getString("success"), "true")) {
//                    getResultCallBack.success(null);
//                } else {
//                    getResultCallBack.failed(jsonObject.getString("message"));
//                }
//            }
//        });
    }

    @Override
    public ServerData getMapData(String userId, final GetResultCallBack getResultCallBack) {
        params.put("methodName", "userLogin");
        params.put("userID", userId);
//        HttpUtils.http(params, new Action1<JSONObject>() {
//            @Override
//            public void call(JSONObject jsonObject) {
//                params.clear();
//                if (TextUtils.equals(jsonObject.getString("success"), "true")) {
//                    getResultCallBack.success(jsonObject.getJSONObject("content"));
//                } else {
//                    getResultCallBack.failed(jsonObject.getString("message"));
//                }
//            }
//        });
        return null;
    }
}

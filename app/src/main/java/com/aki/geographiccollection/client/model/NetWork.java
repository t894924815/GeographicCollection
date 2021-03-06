package com.aki.geographiccollection.client.model;

import android.support.v4.util.ArrayMap;

import com.aki.geographiccollection.client.bean.ServerData;

import com.aki.geographiccollection.client.GeoApplication;
import com.aki.geographiccollection.client.utils.DBUtil;
import com.litesuits.orm.LiteOrm;

import rx.functions.Action1;
//import org.aki.geographiccollection.client.utils.HttpUtils;


/**
 * Created by aki on 2016/8/26.
 */
public class NetWork implements INetWork {


    private final ArrayMap<String, String> params;
    private LiteOrm liteOrm;

    public NetWork() {
        params = GeoApplication.params;
        liteOrm = DBUtil.getDbUtil();

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

    @Override
    public void uploadPhoto(final Action1<ArrayMap<String, String>> ac, byte[] bytes, final String filename, boolean issmall) {
//        params.put("upload");
    }


}

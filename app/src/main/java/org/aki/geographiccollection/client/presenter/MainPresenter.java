package org.aki.geographiccollection.client.presenter;

import com.alibaba.fastjson.JSONObject;

import org.aki.geographiccollection.client.model.GetResultCallBack;
import org.aki.geographiccollection.client.model.INetWork;
import org.aki.geographiccollection.client.model.NetWork;

/**
 * Created by chunr on 2016/8/26.
 */
public class MainPresenter implements IMainPresenter {
    private INetWork network;

    public MainPresenter() {
        network = new NetWork();
    }

    @Override
    public void login(String userName, String password) {
        network.validateLogin(userName, password, new GetResultCallBack() {
            @Override
            public void success(JSONObject content) {

            }

            @Override
            public void failed(String message) {

            }

            @Override
            public void error(String errorCode) {

            }
        });
    }


    @Override
    public void getServerData(String userId) {
        network.getMapData(userId, new GetResultCallBack() {
            @Override
            public void success(JSONObject content) {

            }

            @Override
            public void failed(String message) {

            }

            @Override
            public void error(String errorCode) {

            }
        });
    }
}

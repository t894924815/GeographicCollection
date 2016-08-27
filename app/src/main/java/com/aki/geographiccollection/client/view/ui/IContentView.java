package com.aki.geographiccollection.client.view.ui;

import com.aki.geographiccollection.client.bean.ServerData;

/**
 * Created by aki on 2016/8/26.
 */
public interface IContentView {
    void showLoading();
    void hideLoading();
    void setServerData(ServerData serverData);
}

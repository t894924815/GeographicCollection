package org.aki.geographiccollection.client.view.ui;

import org.aki.geographiccollection.client.bean.ServerData;

/**
 * Created by aki on 2016/8/26.
 */
public interface IContentView {
    void showLoading();
    void hideLoading();
    void setServerData(ServerData serverData);
}

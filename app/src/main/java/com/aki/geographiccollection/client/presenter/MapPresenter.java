package com.aki.geographiccollection.client.presenter;

import com.aki.geographiccollection.client.view.IMapOperator;

/**
 * Created by A04 on 2016/8/25.
 */
public class MapPresenter {
    private IMapOperator iMapOperator;

    public MapPresenter(IMapOperator iMapOperator) {
        this.iMapOperator = iMapOperator;
    }
}

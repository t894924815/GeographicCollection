package org.aki.geographiccollection.client.presenter;

import org.aki.geographiccollection.client.view.IMapOperator;
import org.aki.geographiccollection.client.view.IUiOperator;

/**
 * Created by A04 on 2016/8/25.
 */
public class MapPresenter {
    private IMapOperator iMapOperator;

    public MapPresenter(IMapOperator iMapOperator) {
        this.iMapOperator = iMapOperator;
    }
}

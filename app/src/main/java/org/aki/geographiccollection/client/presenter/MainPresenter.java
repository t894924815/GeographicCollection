package org.aki.geographiccollection.client.presenter;

import org.aki.geographiccollection.client.view.IUiOperator;

/**
 * Created by chunr on 2016/8/26.
 */
public class MainPresenter {
    private IUiOperator iUiOperator;

    public MainPresenter(IUiOperator iUiOperator) {
        this.iUiOperator = iUiOperator;
    }

    public void startPoint() {
        iUiOperator.hideCollectView();
    }
}

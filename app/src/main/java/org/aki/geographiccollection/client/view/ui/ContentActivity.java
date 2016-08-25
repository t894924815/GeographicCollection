package org.aki.geographiccollection.client.view.ui;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;

import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.model.LatLng;

import org.aki.geographiccollection.client.bean.WitchFragment;
import org.aki.geographiccollection.client.presenter.MapPresenter;
import org.aki.geographiccollection.client.presenter.UIPresenter;
import org.aki.geographiccollection.client.view.IMapOperator;
import org.aki.geographiccollection.client.view.IUiOperator;
import org.aki.geographiccollection.client.view.ui.fragment.CollectionFragment;
import org.aki.geographiccollection.client.view.ui.fragment.MessageFragment;

/**
 * Created by aki on 2016/8/25.
 */
public class ContentActivity extends BaseActivity implements IUiOperator, IMapOperator {

    public CollectionFragment collectionFragment;
    public MessageFragment messageFragment;
    private FragmentManager fragmentManager;

    public UIPresenter uiPresenter;
    public MapPresenter mapContentPresenter;

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        initData();
    }

    private void initData(){
        uiPresenter = new UIPresenter(this);
        mapContentPresenter = new MapPresenter(this);

        collectionFragment = new CollectionFragment();
        messageFragment = new MessageFragment();
        fragmentManager = getSupportFragmentManager();
        uiPresenter
    }


    @Override
    public void showMapType() {

    }

    @Override
    public void hideMapType() {

    }

    @Override
    public void updateTopData(String num) {

    }

    @Override
    public void toFragment(Enum e) {
        if(e == WitchFragment.MESSAGE){
            if(null != messageFragment && !messageFragment.isAdded()){
                fragmentManager.beginTransaction().add(android.R.id.content,messageFragment).commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void changePointColor(Drawable drawable, Overlay overlay) {

    }

    @Override
    public void addOverLayPoint(LatLng latLng, MapView mapView) {

    }

    @Override
    public void removeOverLayPoint(LatLng latLng, MapView mapView) {

    }
}

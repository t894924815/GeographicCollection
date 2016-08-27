package com.aki.geographiccollection.client.view.ui;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.model.LatLng;

import com.aki.geographiccollection.client.presenter.MainPresenter;
import com.aki.geographiccollection.client.presenter.MapPresenter;
import com.aki.geographiccollection.client.view.IMapOperator;
import com.aki.geographiccollection.client.view.IUiOperator;
import com.aki.geographiccollection.client.view.ui.fragment.CollectionFragment;
import com.aki.geographiccollection.client.view.ui.fragment.MessageFragment;

import org.aki.geographiccollection.R;

import butterknife.ButterKnife;

/**
 * Created by aki on 2016/8/25.
 */
public class ContentActivity extends BaseActivity implements IUiOperator, IMapOperator {

    private FragmentManager fragmentManager;

    private MainPresenter mainPresenter;
//    private UIPresenter uiPresenter;
    private MapPresenter mapContentPresenter;
    public CollectionFragment collectionFragment;
    public MessageFragment messageFragment;

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    private void initData() {
//        mainPresenter = new MainPresenter(this);
//        uiPresenter = new UIPresenter(this);
//        mapContentPresenter = new MapPresenter(this);

        collectionFragment = new CollectionFragment();
        messageFragment = new MessageFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(android.R.id.content,collectionFragment).commitAllowingStateLoss();
//        uiPresenter.showFragment(WitchFragment.COLLECTION);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

//    public View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.tv_collect:
//                    uiPresenter.startPoint();
//                    break;
//            }
//        }
//    };
//

//    public View.OnTouchListener onTouchListener = new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            switch (motionEvent.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    uiPresenter.moveView(view, motionEvent);
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    uiPresenter.moveView(view, motionEvent);
//                    break;
//                case MotionEvent.ACTION_UP:
//                    break;
//            }
//            return false;
//        }
//    };

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
    public void addFragment(Fragment fragment) {
        fragmentManager.beginTransaction().add(android.R.id.content, fragment).commitAllowingStateLoss();
    }

    @Override
    public void showFragment(Fragment fragment) {
        fragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss();
    }

    @Override
    public void hideFragment(Fragment fragment) {

    }


    @Override
    public void showAimTarget() {

    }

    @Override
    public void hideAimTarget() {

    }

    @Override
    public void moveAimTarget(int x, int y) {

    }

    @Override
    public void hideCollectView() {

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

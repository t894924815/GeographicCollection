package com.aki.geographiccollection.client.view.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aki.geographiccollection.client.GeoApplication;
import com.aki.geographiccollection.client.presenter.UIPresenter;
import com.aki.geographiccollection.client.utils.DisplayUtil;
import com.aki.geographiccollection.client.utils.LocationService;
import com.aki.geographiccollection.client.utils.UIUtils;
import com.aki.geographiccollection.client.view.IUiOperator;
import com.aki.geographiccollection.client.view.ui.ContentActivity;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import org.aki.geographiccollection.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by aki on 2016/8/25.
 */
public class CollectionFragment extends Fragment implements IUiOperator {

    @BindView(R.id.coordinate)
    TextView coordinate;
    @BindView(R.id.tv_tool_title)
    TextView toolTitle;
    @BindView(R.id.iv_refresh)
    ImageView refresh;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_map)
    MapView mMapView;
    @BindView(R.id.flag)
    View flag;
    @BindView(R.id.tv_collect)
    TextView collect;
    @BindView(R.id.iv_aim_target)
    ImageView aimTarget;
    @BindView(R.id.tv_confirm)
    TextView confirm;
    @BindView(R.id.aim_root)
    RelativeLayout aimRoot;
    @BindView(R.id.main_drawer)
    DrawerLayout mainDrawer;
    public ContentActivity contentActivity;

    public BaiduMap mBaiduMap;
    public LocationService locationService;
    private UIPresenter uiPresenter;
    private Marker marker;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_content, container, false);
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        contentActivity = (ContentActivity) context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        super.onDestroy();
        mMapView.removeAllViews();
        mBaiduMap = null;
        bdLocationListener = null;
        if (locationService != null)
            locationService.unregisterListener(null);
        locationService = null;
    }

    private void initView() {
        aimTarget.setOnTouchListener(onTouchListener);

        coordinate.setText(String.valueOf(DisplayUtil.getScreanWidth() / 2) + "," + String.valueOf(DisplayUtil.getScreanHeight() / 2));

    }


    private void initData() {
        uiPresenter = new UIPresenter(this);

        mBaiduMap = mMapView.getMap();

//普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.i("Aki", "///");
                return false;
            }
        });
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (isGet) {
                    markOverLay(latLng);
                }
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });

        locationService = new LocationService(GeoApplication.application);
        locationService.registerListener(bdLocationListener);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        locationService.start();
//卫星地图
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);

//空白地图, 基础地图瓦片将不会被渲染。在地图类型中设置为NONE，将不会使用流量下载基础地图瓦片图层。使用场景：与瓦片图层一起使用，节省流量，提升自定义瓦片图下载速度。
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
    }


    private BDLocationListener bdLocationListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            MyLocationData myLocationData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    .direction(100)
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude())
                    .build();
            MapStatus.Builder builder = new MapStatus.Builder();
            LatLng ll = new LatLng(bdLocation.getLatitude(),
                    bdLocation.getLongitude());
            builder.target(ll).zoom(18.0f);
            mBaiduMap.setMyLocationData(myLocationData);
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            locationService.stop();
        }
    };

    private void refreshMap() {
        locationService.start();
    }

    private void markOverLay(LatLng point) {
        isGet = false;
//定义Maker坐标点
//        LatLng point = new LatLng(39.963175, 116.400244);
//构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.search_near);
//构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
//在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }

    public View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    uiPresenter.moveView(view, motionEvent);
                    break;
                case MotionEvent.ACTION_MOVE:
                    uiPresenter.moveView(view, motionEvent);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            if (view.getId() == R.id.iv_aim_target) {
                return true;
            }
            return false;
        }
    };

    private void startCollect() {
        collect.setVisibility(View.GONE);
        aimTarget.setVisibility(View.VISIBLE);

//        marker = null;
//        OverlayOptions options = new MarkerOptions()
//                .position(new LatLng(mBaiduMap.getLocationData().latitude, mBaiduMap.getLocationData().longitude))  //设置marker的位置
//                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.iv_target))  //设置marker图标
//                .zIndex(9)  //设置marker所在层级
//                .draggable(true);  //设置手势拖拽
//        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
//            @Override
//            public void onMarkerDrag(Marker marker) {
//                Log.i("Aki", ".");
//            }
//
//            @Override
//            public void onMarkerDragEnd(Marker marker) {
//                Log.i("Aki", ".");
//            }
//
//            @Override
//            public void onMarkerDragStart(Marker marker) {
//                Log.i("Aki", ".");
//            }
//        });
//将marker添加到地图上
//        marker = (Marker) (mBaiduMap.addOverlay(options));
    }

    private boolean isGet = false;
    @OnClick({R.id.coordinate, R.id.tv_tool_title, R.id.iv_refresh, R.id.toolbar, R.id.main_map, R.id.tv_collect, R.id.iv_aim_target, R.id.tv_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_refresh:
                Animation operatingAnim = AnimationUtils.loadAnimation(GeoApplication.application, R.anim.rorate);
                LinearInterpolator lin = new LinearInterpolator();
                operatingAnim.setInterpolator(lin);
                refresh.startAnimation(operatingAnim);
                refreshMap();
                break;
            case R.id.tv_collect:
                startCollect();
                break;
            case R.id.tv_confirm:
                isGet = true;
                getPosition(aimTarget.getLeft() + aimTarget.getWidth() / 2.0f,
                        aimTarget.getTop() + aimTarget.getHeight() / 2.0f - toolbar.getHeight());
                break;
        }
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
    public void addFragment(Fragment fragment) {

    }

    @Override
    public void showFragment(Fragment fragment) {

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
        aimTarget.offsetLeftAndRight(x > aimTarget.getLeft() ?
                -aimTarget.getLeft() : (-x > aimRoot.getWidth() - aimTarget.getRight()) ?
                -(aimRoot.getWidth() - aimTarget.getRight()) : -x);
        aimTarget.offsetTopAndBottom(y > (aimTarget.getTop() - toolbar.getHeight()) ?
                -(aimTarget.getTop() - toolbar.getHeight()) : (-y > aimRoot.getHeight() - aimTarget.getBottom()) ?
                aimRoot.getHeight() - aimTarget.getBottom() : -y);
        coordinate.setText(String.valueOf(aimTarget.getLeft() + aimTarget.getWidth() / 2) + "," + String.valueOf(aimTarget.getTop() + aimTarget.getHeight() / 2 - toolbar.getHeight()));
    }

    @Override
    public void hideCollectView() {

    }

    public void getPosition(float x, float y) {
        UIUtils.getInstance().simulateClick(mMapView, x, y);
    }

}

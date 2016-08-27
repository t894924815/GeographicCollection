package com.aki.geographiccollection.client.view;

import android.graphics.drawable.Drawable;

import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.model.LatLng;

/**
 * Created by A04 on 2016/8/25.
 */
public interface IMapOperator {
    public void changePointColor(Drawable drawable,Overlay overlay);
    public void addOverLayPoint(LatLng latLng, MapView mapView);
    public void removeOverLayPoint(LatLng latLng,MapView mapView);
}

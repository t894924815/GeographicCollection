package org.aki.geographiccollection.client.view.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.MapView;

import org.aki.geographiccollection.R;
import org.aki.geographiccollection.client.utils.UIUtils;
import org.aki.geographiccollection.client.view.ui.ContentActivity;

/**
 * Created by aki on 2016/8/25.
 */
public class CollectionFragment extends Fragment {
    public TextView toolTitle, collect;
    public ImageView refresh, aimTarget;
    public Toolbar toolbar;
    public MapView mapView;
    public ContentActivity contentActivity;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolTitle = UIUtils.getInstance().$(view, R.id.tool_title);
        collect = UIUtils.getInstance().$(view,R.id.tv_collect);
        refresh = UIUtils.getInstance().$(view,R.id.iv_refresh);
        aimTarget = UIUtils.getInstance().$(view,R.id.iv_aim_target);
        mapView= UIUtils.getInstance().$(view,R.id.main_map);

        collect.setOnClickListener(contentActivity.onClickListener);
        aimTarget.setOnTouchListener(contentActivity.onTouchListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_content, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        contentActivity = (ContentActivity) context;
    }


}

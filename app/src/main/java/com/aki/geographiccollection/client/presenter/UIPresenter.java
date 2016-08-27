package com.aki.geographiccollection.client.presenter;

import android.view.MotionEvent;
import android.view.View;

import com.aki.geographiccollection.client.view.IUiOperator;

import org.aki.geographiccollection.R;

/**
 * Created by aki on 2016/8/25.
 */
public class UIPresenter {
    private IUiOperator iUiOperator;
    private int currentX;
    private int currentY;

    public UIPresenter(IUiOperator iUiOperator) {
        this.iUiOperator = iUiOperator;
    }

    public void moveView(View view, MotionEvent motionEvent) {
        if (view.getId() == R.id.iv_aim_target && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            currentX = (int) motionEvent.getRawX();
            currentY = (int) motionEvent.getRawY();
        } else if (view.getId() == R.id.iv_aim_target && motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            int x2 = (int) motionEvent.getRawX();
            int y2 = (int) motionEvent.getRawY();
            iUiOperator.moveAimTarget(currentX - x2, currentY - y2);
            currentX = x2;
            currentY = y2;
        }
    }

//    public void showFragment(WitchFragment witch) {
//        if(witch == WitchFragment.COLLECTION){
//            if(!collectionFragment.isAdded()){
//                iUiOperator.addFragment(collectionFragment);
//            }else {
//                iUiOperator.showFragment(collectionFragment);
//            }
//        }
//    }

}

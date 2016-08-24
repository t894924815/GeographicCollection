package org.aki.geographiccollection.client.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import org.aki.geographiccollection.client.GeoApplication;
import org.aki.geographiccollection.client.widgwt.LoadingDialog;

public class BaseActivity extends AppCompatActivity {
    private LoadingDialog loadingDialog;
    private GeoApplication application;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (GeoApplication) getApplication();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loadingDialog != null && loadingDialog.isShowing())
            loadingDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (loadingDialog != null && loadingDialog.isShowing())
            loadingDialog.dismiss();
    }

    public boolean showLoadingDialog(boolean cancelable) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        } else {
            if (loadingDialog.isShowing()) {
                return cancelable;
            }
        }
        loadingDialog.setCancelable(cancelable);
        loadingDialog.show();
        return false;
    }

    public void cancelLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
}

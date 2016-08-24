package org.aki.geographiccollection.widgwt;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Window;

import com.jingxi.smartlife.user.R;
import com.jingxi.smartlife.user.SmartApplication;

import org.aki.geographiccollection.util.GeoApplication;

public class LoadingDialog extends Dialog {


    public LoadingDialog(Context context) {

        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_loading);
        setCanceledOnTouchOutside(false);
        getWindow().setBackgroundDrawable(
                new ColorDrawable(ContextCompat.getColor(GeoApplication.application, android.R.color.transparent)));
    }


}

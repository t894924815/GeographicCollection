package org.aki.geographiccollection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.jingxi.smartlife.user.SmartApplication;
import com.jingxi.smartlife.user.utils.AppUtils;
import com.jingxi.smartlife.user.utils.SpUtil;
import com.jingxi.smartlife.user.widget.LoadingDialog;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;

import org.aki.geographiccollection.util.GeoApplication;

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
        application = (SmartApplication) getApplication();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(SpUtil.getInstance().get("isLogin"))) {
            if (NIMClient.getStatus() != StatusCode.LOGINED) {
                NIMClient.getService(AuthService.class).login(new LoginInfo(SpUtil.getInstance().get("accid"), SpUtil.getInstance().get("token")));
            }
        }
        if ((application.activities.get(application.activities.size() - 1) instanceof HomeActivity) || (application.activities.get(application.activities.size() - 1) instanceof ChatActivity)) {
            NIMClient.getService(MsgService.class).setChattingAccount(MsgService.MSG_CHATTING_ACCOUNT_ALL, SessionTypeEnum.None);
        } else {
            NIMClient.getService(MsgService.class).setChattingAccount(MsgService.MSG_CHATTING_ACCOUNT_NONE, SessionTypeEnum.None);
        }
        if (application.activities == null || application.activities.size() == 0) {
            NIMClient.getService(MsgService.class).setChattingAccount(MsgService.MSG_CHATTING_ACCOUNT_NONE, SessionTypeEnum.None);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loadingDialog != null && loadingDialog.isShowing())
            loadingDialog.dismiss();


        AppUtils.fixInputMethodManagerLeak(this);
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

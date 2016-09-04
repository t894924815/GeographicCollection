package com.aki.geographiccollection.client.presenter;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.aki.geographiccollection.client.bean.LoginRegisterCode;
import com.aki.geographiccollection.client.model.LoginRegisterModel;
import com.aki.geographiccollection.client.utils.SpUtil;
import com.aki.geographiccollection.client.utils.ToastUtil;
import com.aki.geographiccollection.client.view.ui.ILoginRegisterView;
import com.aki.geographiccollection.client.view.ui.fragment.ILoginView;
import com.aki.geographiccollection.client.view.ui.fragment.IRegisterView;

/**
 * Created by wudi on 2016/9/4.
 */
public class LoginRegisterPresenter implements ILoginRegister {

    private ILoginRegisterView iLoginRegisterView;
    private IRegisterView iRegisterView;
    private ILoginView iLoginView;
    private LoginRegisterModel loginRegisterModel;

    public LoginRegisterPresenter(ILoginRegisterView iLoginRegisterView, ILoginView iLoginView, IRegisterView iRegisterView) {
        this.iLoginRegisterView = iLoginRegisterView;
        this.iLoginView = iLoginView;
        this.iRegisterView = iRegisterView;
        loginRegisterModel = new LoginRegisterModel(this);
        iLoginRegisterView.showLoginPage();

    }


    @Override
    public void login(View... views) {
        iLoginRegisterView.showDialog();
        if (views.length != 2) {
            iLoginRegisterView.hideDialog();
            return;
        }
        if (TextUtils.isEmpty(((EditText) views[0]).getText().toString().trim())
                || TextUtils.isEmpty(((EditText) views[1]).getText().toString().trim())) {
            ToastUtil.showToast("账号密码不能为空!");
            iLoginRegisterView.hideDialog();
        }else {
            loginRegisterModel.login(((EditText) views[0]).getText().toString().trim()
                    ,((EditText) views[1]).getText().toString().trim());
        }
    }

    @Override
    public void register(View... views) {
        iLoginRegisterView.showDialog();
        if (views.length != 3) {
            iLoginRegisterView.hideDialog();
            return;
        }
        if (TextUtils.isEmpty(((EditText) views[0]).getText().toString().trim())) {
            iLoginRegisterView.hideDialog();
            ToastUtil.showToast("用户名不能为空！");
        } else {
            if (TextUtils.isEmpty(((EditText) views[1]).getText().toString().trim())
                    || TextUtils.isEmpty(((EditText) views[2]).getText().toString().trim())) {
                iLoginRegisterView.hideDialog();
                ToastUtil.showToast("密码不能为空！");
            } else {
                if (TextUtils.equals(((EditText) views[1]).getText().toString(), ((EditText) views[2]).getText().toString().trim())) {
                    loginRegisterModel.register(((EditText) views[0]).getText().toString().trim(), ((EditText) views[1]).getText().toString().trim());
                }
            }
        }
    }

    @Override
    public void loginSuccess(String userId, String pass) {
        SpUtil.getInstance().put("userId", userId);
        SpUtil.getInstance().put("userPass", pass);

        iLoginRegisterView.jump();
        iLoginRegisterView.hideDialog();
        ToastUtil.showToast("注册成功!正在登陆...");
    }

    @Override
    public void loginFailed(LoginRegisterCode code) {
        iLoginRegisterView.hideDialog();
        if (code == LoginRegisterCode.ERROR_NAME) {
            ToastUtil.showToast("用户名错误!");
        } else if (code == LoginRegisterCode.ERROR_PASS) {
            ToastUtil.showToast("密码错误");
        }else if(code == LoginRegisterCode.SAME_USER){
            ToastUtil.showToast("用户名已被注册，请换一个！");
        }
    }

    @Override
    public void registerSuccess(String userId, String pass) {
        loginRegisterModel.login(userId, pass);
    }

    @Override
    public void registerFailed(LoginRegisterCode code) {

    }


    @Override
    public void clearFragment() {
        iLoginView.clearData();
        iRegisterView.clearData();
    }

    @Override
    public void toRegister() {
        iLoginRegisterView.showRegisterPage();
    }


}

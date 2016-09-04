package com.aki.geographiccollection.client.presenter;

import android.support.v4.app.Fragment;
import android.view.View;

import com.aki.geographiccollection.client.bean.LoginRegisterCode;

/**
 * Created by chunr on 2016/9/4.
 */
public interface ILoginRegister {
    void login(View ...views);

    void loginSuccess(String userId,String pass);

    void loginFailed(LoginRegisterCode code);

    void register(View... views);

    void registerSuccess(String userId,String pass);

    void registerFailed(LoginRegisterCode code);

    void clearFragment();

    void toRegister();
}

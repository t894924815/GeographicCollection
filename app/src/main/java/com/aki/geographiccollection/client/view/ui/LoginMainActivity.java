package com.aki.geographiccollection.client.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

import com.aki.geographiccollection.client.presenter.LoginRegisterPresenter;
import com.aki.geographiccollection.client.utils.SpUtil;
import com.aki.geographiccollection.client.view.ui.fragment.LoginFragment;
import com.aki.geographiccollection.client.view.ui.fragment.RegisterFragment;
import com.amap.api.maps2d.model.Text;

public class LoginMainActivity extends BaseActivity implements ILoginRegisterView {

    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    private FragmentManager manager;
    public LoginRegisterPresenter loginRegisterPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login_main);
        if (!TextUtils.isEmpty(SpUtil.getInstance().get("isLogin"))) {
            finish();
            startActivity(new Intent(this, ContentActivity.class));
        }
        initData();
    }

    private void initData() {
        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();
        manager = getSupportFragmentManager();
        loginRegisterPresenter = new LoginRegisterPresenter(this, loginFragment, registerFragment);
    }

    @Override
    public void showLoginPage() {
        manager.beginTransaction().add(android.R.id.content, loginFragment).commitAllowingStateLoss();
    }

    @Override
    public void showRegisterPage() {
        manager.beginTransaction().add(android.R.id.content, registerFragment).commitAllowingStateLoss();
    }

    @Override
    public void jump() {
        startActivity(new Intent(this, ContentActivity.class));
        finish();
    }

    @Override
    public void showDialog() {
        super.showLoadingDialog(true);
    }

    @Override
    public void hideDialog() {
        super.cancelLoadingDialog();
    }

    @Override
    public void onBackPressed() {
        if (registerFragment.isVisible()) {
            manager.beginTransaction().remove(registerFragment).commitAllowingStateLoss();
            loginRegisterPresenter.clearFragment();
            return;
        }
        super.onBackPressed();
    }
}

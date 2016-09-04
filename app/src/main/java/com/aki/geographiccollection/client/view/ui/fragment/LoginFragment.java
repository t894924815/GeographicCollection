package com.aki.geographiccollection.client.view.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aki.geographiccollection.client.view.ui.LoginMainActivity;

import org.aki.geographiccollection.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chunr on 2016/9/4.
 */
public class LoginFragment extends Fragment implements ILoginView {


    @BindView(R.id.userId)
    EditText userId;
    @BindView(R.id.userPass)
    EditText userPass;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.login)
    Button login;


    private LoginMainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (LoginMainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_login, container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void clearData() {

    }


    @OnClick({R.id.register, R.id.login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register:
                activity.loginRegisterPresenter.toRegister();
                break;
            case R.id.login:
                activity.loginRegisterPresenter.login(userId,userPass);
                break;
        }
    }
}

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
 * Created by wudi on 2016/9/4.
 */
public class RegisterFragment extends Fragment implements IRegisterView {


    @BindView(R.id.text_cancel)
    TextView back;
    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.passWord)
    EditText passWord;
    @BindView(R.id.rePassWord)
    EditText rePassWord;
    @BindView(R.id.register)
    Button register;
    private LoginMainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (LoginMainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_register, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void clearData() {

    }


    @OnClick({R.id.text_cancel, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_cancel:
                activity.onBackPressed();
                break;
            case R.id.register:
                activity.loginRegisterPresenter.register(userName, passWord,rePassWord);
                break;
        }
    }
}

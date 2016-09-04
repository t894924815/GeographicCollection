package com.aki.geographiccollection.client.model;

import android.text.TextUtils;

import com.aki.geographiccollection.client.bean.LoginRegisterCode;
import com.aki.geographiccollection.client.bean.User;
import com.aki.geographiccollection.client.presenter.ILoginRegister;
import com.aki.geographiccollection.client.utils.DBUtil;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;

import java.util.*;

/**
 * Created by chunr on 2016/9/4.
 */
public class LoginRegisterModel implements ILoginRegisterModel {

    private ILoginRegister iLoginRegister;
    private LiteOrm liteOrm;
    private ArrayList<User> userList;

    public LoginRegisterModel(ILoginRegister iLoginRegister) {
        this.iLoginRegister = iLoginRegister;
        liteOrm = DBUtil.getDbUtil();
    }

    @Override
    public void login(String userName, String password) {

        WhereBuilder whereBuilder = new WhereBuilder(User.class, "userName like " + "\'" + userName + "\'", null);
        QueryBuilder<User> queryBuilder = new QueryBuilder<>(User.class).where(whereBuilder);

        userList = liteOrm.query(queryBuilder);
        if (userList.size() == 0) {
            iLoginRegister.loginFailed(LoginRegisterCode.ERROR_NAME);
        } else {
            if (TextUtils.equals(userList.get(0).passWord, password)) {
                iLoginRegister.loginSuccess(userList.get(0).userName, userList.get(0).passWord);
            } else {
                iLoginRegister.loginFailed(LoginRegisterCode.ERROR_PASS);
            }
        }
    }

    @Override
    public void register(String userName, String password) {
        WhereBuilder whereBuilder = new WhereBuilder(User.class, "userName like " + "\'" + userName + "\'", null);
        QueryBuilder<User> queryBuilder = new QueryBuilder<>(User.class).where(whereBuilder);

        userList = liteOrm.query(queryBuilder);
        if (userList.size() == 0) {
            liteOrm.save(new User(userName, password));
            iLoginRegister.registerSuccess(userName,password);
        }else{
            iLoginRegister.loginFailed(LoginRegisterCode.SAME_USER);
        }
    }
}

package com.aki.geographiccollection.client.model;

/**
 * Created by chunr on 2016/9/4.
 */
public interface ILoginRegisterModel {
    void login(String userName, String password);

    void register(String userName, String password);
}

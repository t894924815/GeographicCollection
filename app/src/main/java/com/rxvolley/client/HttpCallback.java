package com.rxvolley.client;

import android.graphics.Bitmap;

import com.rxvolley.http.VolleyError;

import java.util.Map;

/**
 * Http请求回调类<br>
 * <b>创建时间</b> 2014-8-7
 *
 * @author kymjs (http://www.kymjs.com/) .
 * @version 1.4
 */
public abstract class HttpCallback {

    /**
     * 请求开始之前回调
     */
    public void onPreStart() {
    }

    /**
     * 发起Http之前调用(只要是内存缓存中没有就会被调用)
     */
    public void onPreHttp() {
    }

    /**
     * 注意：本方法将在异步调用。
     * Http异步请求成功时在异步回调,并且仅当本方法执行完成才会继续调用onSuccess()
     *
     * @param t 返回的信息
     */
    public void onSuccessInAsync(byte[] t) {
    }

    /**
     * Http请求成功时回调
     *
     * @param t HttpRequest返回信息
     */
    public void onSuccess(String t) {
    }

    /**
     * Http请求成功时回调
     *
     * @param headers HttpRespond头
     * @param t       HttpRequest返回信息
     */
    public void onSuccess(Map<String, String> headers, byte[] t) {
        onSuccess(new String(t));
    }

    /**
     * Http请求失败时回调
     *
     * @param errorNo 错误码
     * @param strMsg  错误原因
     */
    public void onFailure(int errorNo, String strMsg) {
    }

    /**
     * Http请求失败时回调
     * 仅Http网络请求中有效
     */
    public void onFailure(VolleyError error) {
    }

    /**
     * Http请求结束后回调
     */
    public void onFinish() {
    }

    /**
     * 仅请求bitmap中有效
     */
    public void onSuccess(Map<String, String> headers, Bitmap bitmap) {
    }
}

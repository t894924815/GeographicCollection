package com.rxvolley.interf;


import com.rxvolley.http.Request;
import com.rxvolley.toolbox.HttpParamsEntry;
import com.rxvolley.http.URLHttpResponse;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Http请求端定义
 *
 * @author kymjs (http://www.kymjs.com/) on 12/17/15.
 */
public interface IHttpStack {

    /**
     * 让Http请求端去发起一个Request
     *
     * @param request           一次实际请求集合
     * @param additionalHeaders Http请求头
     * @return 一个Http响应
     */
    URLHttpResponse performRequest(Request<?> request, ArrayList<HttpParamsEntry> additionalHeaders)
            throws IOException;

}
package com.rxvolley.rx;

import java.util.Map;

/**
 * 仅用于RxJava返回的封装数据(结构体封装)
 *
 * @author kymjs (http://www.kymjs.com/) on 12/22/15.
 */
public class Result {

    public Map<String, String> header;
    public byte[] data;
    public String url;

    public Result(String url, Map<String, String> header, byte[] data) {
        this.header = header;
        this.data = data;
        this.url = url;
    }
}

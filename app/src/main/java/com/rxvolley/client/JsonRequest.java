
package com.rxvolley.client;


import android.util.Log;

import com.rxvolley.RxVolley;
import com.rxvolley.http.HttpHeaderParser;
import com.rxvolley.http.NetworkResponse;
import com.rxvolley.http.Request;
import com.rxvolley.http.Response;
import com.rxvolley.toolbox.HttpParamsEntry;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 用来发起application/json格式的请求的，我们平时所使用的是form表单提交的参数，而使用JsonRequest提交的是json参数。
 */
public class JsonRequest extends Request<byte[]> {

    private final String mRequestBody;
    private final HttpParams mParams;

    public JsonRequest(RequestConfig config, HttpParams params, HttpCallback callback) {
        super(config, callback);
        mRequestBody = params.getJsonParams();
        mParams = params;
    }

    @Override
    public ArrayList<HttpParamsEntry> getHeaders() {
        return mParams.getHeaders();
    }

    @Override
    protected void deliverResponse(ArrayList<HttpParamsEntry> headers, byte[] response) {
        if (mCallback != null) {
            HashMap<String, String> map = new HashMap<>(headers.size());
            for (HttpParamsEntry entry : headers) {
                map.put(entry.k, entry.v);
            }
            mCallback.onSuccess(map, response);
        }
    }

    @Override
    public Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        return Response.success(response.data, response.headers,
                HttpHeaderParser.parseCacheHeaders(getUseServerControl(), getCacheTime(),
                        response));
    }

    @Override
    public String getBodyContentType() {
        return String.format("application/json; charset=%s", getConfig().mEncoding);
    }

    @Override
    public String getCacheKey() {
        if (getMethod() == RxVolley.Method.POST) {
            return getUrl() + mParams.getJsonParams();
        } else {
            return getUrl();
        }
    }

    @Override
    public byte[] getBody() {
        try {
            return mRequestBody == null ? null : mRequestBody.getBytes(getConfig().mEncoding);
        } catch (UnsupportedEncodingException uee) {
            Log.e("error",String.format("Unsupported Encoding while trying to get the bytes of %s" +
                    " using %s", mRequestBody, getConfig().mEncoding));
            return null;
        }
    }

    @Override
    public Priority getPriority() {
        return Priority.IMMEDIATE;
    }
}

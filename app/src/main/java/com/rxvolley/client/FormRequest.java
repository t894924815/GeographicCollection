package com.rxvolley.client;


import android.util.Log;

import com.rxvolley.RxVolley;
import com.rxvolley.http.Request;
import com.rxvolley.toolbox.HttpParamsEntry;
import com.rxvolley.http.HttpHeaderParser;
import com.rxvolley.http.NetworkResponse;
import com.rxvolley.http.Response;

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Form表单形式的Http请求
 *
 */
public class FormRequest extends Request<byte[]> {

    private final HttpParams mParams;

    public FormRequest(RequestConfig config, HttpParams params, HttpCallback callback) {
        super(config, callback);
        if (params == null) {
            params = new HttpParams();
        }
        this.mParams = params;
    }

    @Override
    public String getCacheKey() {
        if (getMethod() == RxVolley.Method.POST) {
            return getUrl() + mParams.getUrlParams();
        } else {
            return getUrl();
        }
    }

    @Override
    public String getBodyContentType() {
        if (mParams.getContentType() != null) {
            return mParams.getContentType();
        } else {
            return super.getBodyContentType();
        }
    }

    @Override
    public ArrayList<HttpParamsEntry> getHeaders() {
        return mParams.getHeaders();
    }

    @Override
    public byte[] getBody() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            if (mProgressListener != null) {
                mParams.writeTo(new CountingOutputStream(bos, mParams.getContentLength(),
                        mProgressListener));
            } else {
                mParams.writeTo(bos);
            }
        } catch (IOException e) {
            Log.e("error","FormRequest#getBody()--->IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }

    @Override
    public Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        return Response.success(response.data, response.headers,
                HttpHeaderParser.parseCacheHeaders(getUseServerControl(), getCacheTime(),
                        response));
    }

    @Override
    protected void deliverResponse(ArrayList<HttpParamsEntry> headers, final byte[] response) {
        if (mCallback != null) {
            HashMap<String, String> map = new HashMap<>(headers.size());
            for (HttpParamsEntry entry : headers) {
                map.put(entry.k, entry.v);
            }
            mCallback.onSuccess(map, response);
        }
    }

    @Override
    public Priority getPriority() {
        return Priority.IMMEDIATE;
    }

    public static class CountingOutputStream extends FilterOutputStream {
        private final ProgressListener progListener;
        private long transferred;
        private long fileLength;

        public CountingOutputStream(final OutputStream out, long fileLength,
                                    final ProgressListener listener) {
            super(out);
            this.fileLength = fileLength;
            this.progListener = listener;
            this.transferred = 0;
        }

        public void write(int b) throws IOException {
            out.write(b);
            if (progListener != null) {
                this.transferred++;
                if ((transferred % 20 == 0) && (transferred <= fileLength)) {
                    RxVolley.getRequestQueue().getDelivery().postProgress(this.progListener,
                            this.transferred, fileLength);
                }
            }
        }
    }
}

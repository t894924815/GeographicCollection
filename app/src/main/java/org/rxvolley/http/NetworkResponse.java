
package org.rxvolley.http;


import org.rxvolley.toolbox.HttpStatus;

import java.util.Collections;
import java.util.Map;

/**
 * {@link Network#performRequest(Request)}.
 * 从NetWork执行器返回的Http响应，包含了本次响应是成功还是失败，请求头，响应内容，HTTP状态码
 *
 * @author kymjs (http://www.kymjs.com/) .
 */
public class NetworkResponse {
    /**
     * Creates a new network response.
     *
     * @param statusCode  the HTTP status code
     * @param data        Response body
     * @param headers     Headers returned with this response, or null for none
     * @param notModified True if the server returned a 304 and the data was already in cache
     */
    public NetworkResponse(int statusCode, byte[] data, Map<String, String> headers,
                           boolean notModified) {
        this.statusCode = statusCode;
        this.data = data;
        this.headers = headers;
        this.notModified = notModified;
    }

    public NetworkResponse(byte[] data) {
        this(HttpStatus.SC_OK, data, Collections.<String, String>emptyMap(), false);
    }

    public NetworkResponse(byte[] data, Map<String, String> headers) {
        this(HttpStatus.SC_OK, data, headers, false);
    }

    /**
     * The HTTP status code.
     */
    public final int statusCode;

    /**
     * Raw data from this response.
     */
    public final byte[] data;

    /**
     * Response headers.
     */
    public final Map<String, String> headers;

    /**
     * 如果服务器返回304(Not Modified)，则为true
     */
    public final boolean notModified;
}
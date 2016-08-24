package org.rxvolley.interf;


import org.rxvolley.client.ProgressListener;
import org.rxvolley.http.Request;
import org.rxvolley.http.Response;
import org.rxvolley.http.VolleyError;

/**
 * 分发器，将异步线程中的结果响应到UI线程中
 *
 * @author kymjs (http://www.kymjs.com/).
 */
public interface IDelivery {
    /**
     * 分发响应结果
     *
     */
    void postResponse(Request<?> request, Response<?> response);

    /**
     * 分发Failure事件
     *
     * @param request 请求
     * @param error   异常原因
     */
    void postError(Request<?> request, VolleyError error);

    void postResponse(Request<?> request, Response<?> response, Runnable runnable);

    /**
     * 分发当Http请求开始时的事件
     */
    void postStartHttp(Request<?> request);

    /**
     * 进度
     *
     * @param transferredBytes 进度
     * @param totalSize        总量
     */
    void postProgress(ProgressListener listener, long transferredBytes, long totalSize);
}

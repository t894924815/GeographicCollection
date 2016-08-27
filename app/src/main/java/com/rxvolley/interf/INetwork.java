
package com.rxvolley.interf;


import com.rxvolley.http.NetworkResponse;
import com.rxvolley.http.Request;
import com.rxvolley.http.VolleyError;

/**
 * An interface for performing requests.
 */
public interface INetwork {
    /**
     * Performs the specified request.
     *
     * @param request Request to process
     * @return A {@link NetworkResponse} with data and caching metadata; will
     * never be null
     * @throws VolleyError on errors
     */
    NetworkResponse performRequest(Request<?> request) throws VolleyError;
}

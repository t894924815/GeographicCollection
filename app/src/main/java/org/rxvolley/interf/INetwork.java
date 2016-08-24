
package org.rxvolley.interf;


import org.rxvolley.http.NetworkResponse;
import org.rxvolley.http.Request;
import org.rxvolley.http.VolleyError;

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

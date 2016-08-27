
package com.rxvolley.client;

/**
 * Callback interface for delivering the progress of the responses.
 *
 * @author Jordan Gout (https://github.com/DWorkS/VolleyPlus) on 1/20/16.
 */
public interface ProgressListener {
    /**
     * Callback method thats called on each byte transfer.
     */
    void onProgress(long transferredBytes, long totalSize);
}

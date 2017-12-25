package com.myplas.q.common.netresquset;

/**
 * Created by 黄双 on 2017/12/25 0025.
 */

public interface ProgressListener {
    void onProgress(long currentBytes, long contentLength, boolean done, int type);
}

package com.myplas.q.common.net;

/**
 * Created by 黄双 on 2017/12/25 0025.
 */

class ProgressModel {
    private long currentBytes;
    private long contentLength;
    private boolean done;

    public ProgressModel(long currentBytes, long contentLength, boolean done) {
        this.currentBytes = currentBytes;
        this.contentLength = contentLength;
        this.done = done;
    }

    public long getCurrentBytes() {
        return currentBytes;
    }

    public long getContentLength() {
        return contentLength;
    }

    public boolean isDone() {
        return done;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public void setCurrentBytes(long currentBytes) {
        this.currentBytes = currentBytes;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}

package com.myplas.q.common.net;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * @author 黄双
 * @date 2017/12/25 0025
 */

public class ProgressRequestBody extends RequestBody {
    public static final int UPDATE = 0x01;
    private RequestBody requestBody;
    private ProgressListener mListener;
    private BufferedSink bufferedSink;
    private MyHandler myHandler;
    private int type;

    public ProgressRequestBody(RequestBody body, ProgressListener listener, int type) {
        requestBody = body;
        mListener = listener;
        this.type = type;
        if (myHandler == null) {
            myHandler = new MyHandler();
        }
    }

    class MyHandler extends Handler {
        //放在主线程中显示
        public MyHandler() {
            super(Looper.getMainLooper());
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE:
                    ProgressModel progressModel = (ProgressModel) msg.obj;
                    if (mListener != null) {
                        mListener.onProgress(progressModel.getCurrentBytes(), progressModel.getContentLength(), progressModel.isDone(), type);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return requestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) {

        try {
            if (bufferedSink == null) {
                bufferedSink = Okio.buffer(sink(sink));
            }
            //写入
            requestBody.writeTo(bufferedSink);
            //刷新
            bufferedSink.flush();
        } catch (Exception e) {

        }
    }

    private Sink sink(BufferedSink sink) {

        return new ForwardingSink(sink) {
            long bytesWritten = 0L;
            long contentLength = 0L;

            @Override
            public void write(Buffer source, long byteCount) {
                try {
                    super.write(source, byteCount);
                    if (contentLength == 0) {
                        contentLength = contentLength();
                    }
                    bytesWritten += byteCount;
                    //回调
                    Message msg = Message.obtain();
                    msg.what = UPDATE;
                    msg.obj = new ProgressModel(bytesWritten, contentLength, bytesWritten == contentLength);
                    myHandler.sendMessage(msg);
                } catch (Exception e) {
                }

            }
        };
    }
}

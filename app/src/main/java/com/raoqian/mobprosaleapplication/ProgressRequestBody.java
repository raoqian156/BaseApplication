/**
 * Copyright 2017 JessYan
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.raoqian.mobprosaleapplication;

import android.os.SystemClock;
import android.util.Log;

import com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject.HttpProgressListener;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * ================================================
 * 继承于 {@link RequestBody}, 通过此类获取 Okhttp 上传的二进制数据
 * <p>
 * Created by JessYan on 02/06/2017 18:05
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class ProgressRequestBody extends RequestBody {

    protected int mRefreshTime;
    protected final RequestBody mDelegate;
    private BufferedSink mBufferedSink;
    private HttpProgressListener mHttpProgressListener;


    public ProgressRequestBody(RequestBody delegate, int refreshTime, HttpProgressListener httpProgressListener) {
        this.mDelegate = delegate;
        this.mRefreshTime = refreshTime;
        this.mHttpProgressListener = httpProgressListener;
    }

    @Override
    public MediaType contentType() {
        return mDelegate.contentType();
    }

    @Override
    public long contentLength() {
        try {
            return mDelegate.contentLength();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (mBufferedSink == null) {
            mBufferedSink = Okio.buffer(new CountingSink(sink));
        }
        try {
            mDelegate.writeTo(mBufferedSink);
            mBufferedSink.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected final class CountingSink extends ForwardingSink {
        private long totalBytesRead = 0L;
        private long lastRefreshTime = 0L;  //最后一次刷新的时间
        private long tempSize = 0L;

        public CountingSink(Sink delegate) {
            super(delegate);
        }

        public long getContentLength() {
            return contentLength;
        }

        public void setContentLength(long contentLength) {
            this.contentLength = contentLength;
        }

        private long contentLength = 0;

        @Override
        public void write(Buffer source, long byteCount) {
            try {
                super.write(source, byteCount);
            } catch (IOException e) {
                mHttpProgressListener.onFail("upload.error ->" + mDelegate.toString(), e.getMessage());
                return;
            }
            if (getContentLength() == 0) { //避免重复调用 contentLength()
                setContentLength(contentLength());
            }
            totalBytesRead += byteCount;
            tempSize += byteCount;
            long curTime = SystemClock.elapsedRealtime();
            if (curTime - lastRefreshTime >= mRefreshTime || totalBytesRead == getContentLength()) {
                final long finalTempSize = tempSize;
                final long finalTotalBytesRead = totalBytesRead;
                final long finalIntervalTime = curTime - lastRefreshTime;
                float rate = finalTempSize / 1024F / (finalIntervalTime / 1000F);
                Log.e("ProgressRequestBody", "上传速度 ->" + rate + "(kb/s)");//当前已上传总长度

                mHttpProgressListener.onProgress(finalTotalBytesRead, getContentLength());
                this.lastRefreshTime = curTime;
                this.tempSize = 0;
            }
        }
    }
}

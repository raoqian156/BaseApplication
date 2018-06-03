package com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/14.
 */

public abstract class HttpProcessor {

    private Handler mHandler = new Handler(Looper.getMainLooper());

    protected  void refuseView(long total, long sum, HttpProgressListener listener) {
        mHandler.post(() -> listener.onProgress(sum, total));
    }

    protected <CALL extends HttpCallBackImpl> void backOnFail(String url,String reason, CALL listener) {
        mHandler.post(() -> listener.onFail(url,reason));
    }

    protected <CALL extends HttpCallBackImpl> void backOnSuccess(String url,String content, CALL listener) {
        Log.e("HttpProcessor", "content = "+content);
        mHandler.post(() -> listener.onSuccess(url,content,System.currentTimeMillis()));
    }

    // http 协议的四种动作 get post put delete
    public abstract void get(String url, Map<String, Object> params, HttpCallBack callBack);

    public abstract void post(String url, Map<String, Object> params, HttpCallBack callBack);

    public abstract void uploadFile(String url, HashMap<String, Object> map, String img, String filePath, HttpProgressListener callBack);

    public abstract void download(String url, String saveDir, HttpProgressListener listener);
}

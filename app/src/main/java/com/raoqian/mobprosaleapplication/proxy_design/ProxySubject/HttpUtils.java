package com.raoqian.mobprosaleapplication.proxy_design.ProxySubject;

import android.os.Environment;
import android.util.Log;

import com.raoqian.mobprosaleapplication.bean.NetCashInfo;
import com.raoqian.mobprosaleapplication.db.DatUtil;
import com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject.HttpCallBack;
import com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject.HttpProcessor;
import com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject.HttpProgressListener;

import java.util.HashMap;
import java.util.Map;

import static com.raoqian.mobprosaleapplication.db.DatUtil.DO_NOT_SAVE;

/**
 * Created by Administrator on 2018/5/14.
 */

public class HttpUtils {
    private static HttpProcessor httpProcessor;
    private static HttpUtils httpUtils;

    private HttpUtils() {
    }

    // 单例  
    public static HttpUtils getInstance() {
        if (httpUtils == null) {
            synchronized (HttpUtils.class) {
                if (httpUtils == null)
                    httpUtils = new HttpUtils();
            }
        }
        return httpUtils;
    }

    public static void init(HttpProcessor processor) {
        httpProcessor = processor;
    }

    public static void postTest(HttpCallBack callBack) {
        //http://www.29160047.com/tools/region/getRegions/110101
        String url = "http://www.29160047.com/resources/";
        Map<String, Object> params = new HashMap<>();
        getInstance().post(url, params, callBack);
    }

    public static void putFileTest(String fileName, HttpProgressListener httpProgressListener) {
        String url = "http://193.112.162.47//tools/fileUpDo/fileUp";
        HashMap<String, Object> params = new HashMap<>();
//        params.put("status", 0);
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + fileName;
        Log.e("HttpUtils", "filePath = " + filePath);
        getInstance().putFile(url, params, fileName, filePath, httpProgressListener);


//        String url = "http://193.112.162.47/resources/mp4/blqx.mp4";
//        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
//        getInstance().download(url, filePath, httpProgressListener);

    }

    public void get(String url, Map<String, Object> params, HttpCallBack callBack) {
        httpProcessor.get(url, params, callBack);
    }

    public void post(String url, Map<String, Object> params, final HttpCallBack callBacker) {
        HttpCallBack realCall = new HttpCallBack(callBacker) {

            @Override
            public void onSuccess(Object o) {
                callBacker.onSuccess(o);
            }

            @Override
            protected void onSaveSuccess(String url, String content, long successTime) {
                NetCashInfo cashInfo = new NetCashInfo(url, successTime, content);
                DatUtil.saveNetInfo(cashInfo);
            }

            @Override
            protected boolean onFailRelease(String url, String reason) {
                NetCashInfo cashInfo = DatUtil.getNetInfo(url);
                if (cashInfo != null) {
                    Log.e("HttpUtils", "content = " + cashInfo.getContent());
                    super.onSuccess(url, cashInfo.getContent(), DO_NOT_SAVE);
                }
                return true;
            }


            @Override
            public void onFail(String reason) {

            }
        };
        httpProcessor.post(url, params, realCall);
    }

    private void putFile(String path, HashMap<String, Object> map, String fileName, String filePath, HttpProgressListener callBack) {
        httpProcessor.uploadFile(path, map, fileName, filePath, callBack);
    }

    private void download(String url, String saveDir, HttpProgressListener listener) {
        httpProcessor.download(url, saveDir, listener);
    }

}

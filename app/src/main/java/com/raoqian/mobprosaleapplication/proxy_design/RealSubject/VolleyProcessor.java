package com.raoqian.mobprosaleapplication.proxy_design.RealSubject;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject.HttpCallBack;
import com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject.HttpProcessor;
import com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject.HttpProgressListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/14.
 */

public class VolleyProcessor extends HttpProcessor {
    private RequestQueue requestQueue;

    public VolleyProcessor(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void get(String url, Map<String, Object> params, final HttpCallBack callBack) {
        StringRequest request = new StringRequest(Request.Method.GET, url,
                response ->callBack.onSuccess(url,response, System.currentTimeMillis()),
                error -> callBack.onFail(url,error.toString()));
        requestQueue.add(request);
    }


    @Override
    public void post(String url, final Map<String, Object> params, final HttpCallBack callBack) {
        StringRequest request = new StringRequest(url, response -> {
            Log.e("VolleyProcessor", "response = " + response);
            callBack.onSuccess(url,response, System.currentTimeMillis());

        }, error -> callBack.onFail(url,error.toString())) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //将Map<String, Object> 转为 <String, String>
                Map<String, String> newMap = new HashMap<String, String>();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    newMap.put(entry.getKey(), (String) entry.getValue());
                }
                return newMap;
            }
        };
        requestQueue.add(request);
    }

    @Override
    public void uploadFile(String url, HashMap<String, Object> map, String img, String filePath, HttpProgressListener callBack) {

    }
    @Override
    public void download(String url, String saveDir, HttpProgressListener listener) {

    }


}

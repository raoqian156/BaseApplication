package com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject;

import com.raoqian.mobprosaleapplication.proxy_design.ProxySubject.FormatUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2018/5/14.
 */

public abstract class HttpCallBack<Result> implements HttpCallBackImpl {
    public HttpCallBack() {
        setClass(null);
    }

    public HttpCallBack(HttpCallBack pass) {
        setClass(analysisClassInfo(pass));
    }

    @Override
    public void onSuccess(String key, String content, long successTime) {
        Class<?> clz = analysisClassInfo(this);
        Result result = FormatUtil.getDataFromString(content, clz);
//        Log.e("HttpCallBack", "content = " + content);
//        Gson gson = new Gson();
//        DataTest result = gson.fromJson(content, DataTest.class);
        onSuccess(result);
        onSaveSuccess(key, content, successTime);
    }


    public abstract void onSuccess(Result result);

    public abstract void onFail(String reason);

    private Class<?> mClass;

    public void setClass(Class<?> clazz) {
        this.mClass = clazz;
    }

    private Class<?> analysisClassInfo(HttpCallBack<Result> obj) {
        if (mClass != null) {
            return mClass;
        }
        Type type = obj.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) type).getActualTypeArguments();
        return (Class<?>) params[0];
    }

    @Override
    public void onFail(String url, String reason) {
        if (onFailRelease(url, reason)) {
            return;
        }
        onFail(reason);
    }

    protected boolean onFailRelease(String url, String reason) {
        // TODO: 2018/5/18 加载失败,释放缓存
        return false;
    }

    protected void onSaveSuccess(String url, String content, long successTime) {
        // TODO: 2018/5/18 缓存成功数据

    }
}

package com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject;

/**
 * Created by Administrator on 2018/5/14.
 */

public interface HttpCallBackImpl {

    void onSuccess(String key, String content, long l);


    void onFail(String url, String reason);

}

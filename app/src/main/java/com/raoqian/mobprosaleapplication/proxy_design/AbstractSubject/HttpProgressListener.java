package com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject;

/**
 * Created by Administrator on 2018/5/16.
 */

public interface HttpProgressListener extends HttpCallBackImpl {
    /**
     * 单位:B 字节
     *
     * @param finishValue 已完成数量
     * @param totalValue  总数量
     */
    void onProgress(float finishValue, float totalValue);
}

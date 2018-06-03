package com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject;

/**
 * Created by Administrator on 2018/5/15.
 */

public interface FormatProcessor {
    <Result> Result getDataFromString(String data, Class<?> clz);
}

package com.raoqian.mobprosaleapplication.proxy_design.RealSubject;

import com.google.gson.Gson;
import com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject.FormatProcessor;

/**
 * Created by Administrator on 2018/5/15.
 */

public class GSONFormater implements FormatProcessor {

//    @Override
//    public Result getDataFromString(String inputData, Class<?> clz) {
//        Gson gson = new Gson();
//        Class<?> clz = analysisClassInfo(this);
//        return (Result) gson.fromJson(inputData, clz);
//    }

    @Override
    public <Result> Result getDataFromString(String inputData, Class<?> clz) {
        Gson gson = new Gson();
        return (Result) gson.fromJson(inputData, clz);
    }
}

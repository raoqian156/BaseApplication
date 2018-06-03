package com.raoqian.mobprosaleapplication.proxy_design.ProxySubject;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject.FormatProcessor;

/**
 * Created by Administrator on 2018/5/15.
 */

public class FormatUtil {
    private static FormatProcessor formatProcessor;
    private static FormatUtil mFormatUtil;

    private FormatUtil() {
    }

    private static FormatUtil instance() {
        if (mFormatUtil == null) {
            synchronized (FormatUtil.class) {
                mFormatUtil = new FormatUtil();
            }
        }
        return mFormatUtil;
    }

    public static void init(FormatProcessor formatProcessor) {
        FormatUtil.formatProcessor = formatProcessor;
    }

    public static <Result> Result getDataFromString(String content, Class<?> clz) {
        if (!TextUtils.isEmpty(content)) {
            return instance().getData(content, clz);
        } else {
            Log.e("FormatUtil", "ERROR.content is empty");
            return null;
        }
    }

    private <Result> Result getData(String input, Class<?> clz) {
//        Object obj = formatProcessor.getDataFromString(input,clz);
        Object obj = new Gson().fromJson(input, clz);
        return (Result) obj;
    }
}

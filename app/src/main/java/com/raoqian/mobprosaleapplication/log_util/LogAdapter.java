package com.raoqian.mobprosaleapplication.log_util;

/**
 * Created by Administrator on 2018/5/18.
 */

public interface LogAdapter  {
    void d(String tag, String message);

    void e(String tag, String message);

    void w(String tag, String message);

    void i(String tag, String message);

    void v(String tag, String message);

    void wtf(String tag, String message);
}

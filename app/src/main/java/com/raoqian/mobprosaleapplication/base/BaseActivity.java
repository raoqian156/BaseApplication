package com.raoqian.mobprosaleapplication.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;


/**
 * Created by Administrator on 2018/5/29.
 */

public class BaseActivity extends FragmentActivity {

    private static Handler MAIN = new Handler(Looper.getMainLooper());
    public final static int MAIN_COLOR_RED = 255, MAIN_COLOR_GREEN = 0, MAIN_COLOR_BLUE = 17;

    public static Handler getMain(){
        return MAIN;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

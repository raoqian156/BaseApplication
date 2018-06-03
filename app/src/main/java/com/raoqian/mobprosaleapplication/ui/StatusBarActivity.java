package com.raoqian.mobprosaleapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.raoqian.mobprosaleapplication.R;
import com.raoqian.mobprosaleapplication.utils.StatusBarUtil;

public class StatusBarActivity extends Activity {

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //取消标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);
        view = findViewById(R.id.parentPanel);
        StatusBarUtil.setTransparentForImageView(this, null);
    }
}

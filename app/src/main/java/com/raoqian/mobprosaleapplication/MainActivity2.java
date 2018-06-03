package com.raoqian.mobprosaleapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject.HttpCallBack;
import com.raoqian.mobprosaleapplication.proxy_design.ProxySubject.HttpUtils;
import com.raoqian.mobprosaleapplication.zhujie.ViewById;
import com.raoqian.mobprosaleapplication.zhujie.ViewUtils;

import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {

    @ViewById(R.id.text)
    EditText textView;
//    @ViewById(R.id.show)
//    TextView showView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ViewUtils.inject(this);

//        HttpUtils.postTest(new HttpCallBack<DataTest>() {
//            @Override
//            public void onSuccess(Object dataTest) {
//                TLog.bean("MainActivity2", dataTest);
//            }
//
//            @Override
//            public void onFail(String reason) {
//
//            }
//        });
//        ImageLoaderUtils.show("https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=bdac238024381f3081198ba999004c67/6159252dd42a2834171827b357b5c9ea14cebfcf.jpg",
//                imageView, R.mipmap.ic_launcher);
    }


    public void test(View view) {
        String url = textView.getText().toString();

        HttpUtils.getInstance().post(url, new HashMap<>(), new HttpCallBack<String>() {
            @Override
            public void onSuccess(String result) {
//                showView.setText("result:\n" + result);
            }

            @Override
            public void onFail(String reason) {

            }
        });
    }
}

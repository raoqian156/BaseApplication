package com.raoqian.mobprosaleapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.raoqian.mobprosaleapplication.bean.AA;
import com.raoqian.mobprosaleapplication.bean.Shop;
import com.raoqian.mobprosaleapplication.db.DatUtil;
import com.raoqian.mobprosaleapplication.zhujie.OnClick;
import com.raoqian.mobprosaleapplication.zhujie.ReadFile;
import com.raoqian.mobprosaleapplication.zhujie.ViewById;

import java.util.List;

public class MainActivityCopy extends AppCompatActivity {

    @ViewById(R.id.text)
    EditText textView;
    @ViewById(R.id.image)
    ImageView imageView;
    //    E:\AndroidCode\MobProSaleApplication\local.properties
    @ReadFile({"/storage/sdcard0/Download/server.properties", "port", "max", "min", "serverName"})
    AA properties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
//        ViewUtils.inject(this);
//        ReadUtils.inject(this);
//        properties=readFile("/storage/sdcard0/Download/local.properties");
//        Log.e("MainActivityCopy", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/aa.properties");
//        if (properties != null) {
//            Log.e("MainActivityCopy", "properties ==>> " + properties.toString() + " shuzi " + properties.getMin() * 8);
//        } else {
//            Log.e("MainActivityCopy", "properties ==>> null");
//        }
//        textView=findViewById(R.id.text);

//        HttpUtils.postTest(new HttpCallBack<DataTest>() {
//            @Override
//            public void onSuccess(DataTest dataTest) {
//                Log.e("MainActivityCopy", "da ===>" + dataTest.getFileNumber());
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

    private void test() {
//        MethodSpec main = MethodSpec.methodBuilder("main")
//                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
//                .returns(void.class)
//                .addParameter(String[].class, "args")
//                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
//                .build();

    }

    @OnClick(R.id.text)
    private void click() {
        Toast.makeText(this, "aga ", Toast.LENGTH_SHORT).show();
    }

    public void start(View view) {
        //Long id, String name, String price, int sell_num, String image_url,
        // String address, int type
        Shop shop = new Shop();
        shop.setName("shenme");
        DatUtil.insertCart(shop);
    }

    public void check(View view) {
        List<Shop> shops = DatUtil.queryCart();
        if (shops != null && shops.size() > 0) {
            for (Shop shop : shops) {
                Log.e("MainActivityCopy", "show = " + shop);
            }
        } else {
            Log.e("MainActivityCopy", "show = null");
        }
//        User data = RealmUtil.findData(User.class);
//        if (data != null) {
//            Log.e("MainActivityCopy", "data = " + data.toString());
//        } else {
//            Log.e("MainActivityCopy", "data = null");
//        }
    }

    public void test(View view) {
//        Logger2.lineHere(this);
//        startActivity(new Intent(this, MainActivity2.class));
//        String url = textView.getText().toString();
//        HttpUtils.putFileTest(url, new HttpProgressListener() {
//            @Override
//            public void onProgress(float finishValue, float totalValue) {
//                textView.setText(finishValue + "/" + totalValue);
//                Log.e("MainActivityCopy--", finishValue + "/" + totalValue);
//            }
//
//            @Override
//            public void onSuccess(String url, String content) {
//
//            }
//
//            @Override
//            public void onFail(String url, String reason) {
//
//            }
//        });
    }

}

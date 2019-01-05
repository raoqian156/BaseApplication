package com.raoqian.mobprosaleapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;

import com.raoqian.mobprosaleapplication.base.BaseActivity;
import com.raoqian.mobprosaleapplication.base.BaseFragment;
import com.raoqian.mobprosaleapplication.base.recycler.CommentRecyclerAdapter;
import com.raoqian.mobprosaleapplication.ui.HomeFragment;

/**
 * Created by Administrator on 2018/5/26
 */

public class TestActivity extends BaseActivity {

    RecyclerView recyclerView;
    CommentRecyclerAdapter mAdapter;
    BaseFragment testFragment;
    Handler handler = new Handler(Looper.myLooper());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        Log.e("TestActivity.LINE", "KeyEvent.KEYCODE_BACK= " + KeyEvent.KEYCODE_BACK);
        testFragment = new HomeFragment();
        new Thread(() -> {
            Bundle bundle = new Bundle();
            bundle.putBoolean("test", true);
            testFragment.setArguments(bundle);
            Log.e("TestActivity", "Thread:test. setTest ");
        }).start();
        handler.postDelayed(() -> {
            Bundle bundle = new Bundle();
            bundle.putBoolean("test", true);
            testFragment.setArguments(bundle);
            Log.e("TestActivity", "onCreate:test. setTest ");
        }, 100);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.list_container, testFragment);
        fragmentTransaction.commit();

//        initRecycler1();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            Log.e("TestActivity.LINE", "event.getAction() = " + event.getAction());
//            Toast.makeText(this, "点击了", Toast.LENGTH_LONG).show();
//            return true;
//        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //    private void initRecycler1() {
//        recyclerView = findViewById(R.id.recycler);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(llm);
//        SparseArray<Class<? extends BaseViewHolder>> maps = new SparseArray<>();
//
//
//        maps.put(R.layout.item_spec_input, TestHolder.class);
//        maps.put(R.layout.item_spec_input2, TestHolder2.class);
//        mAdapter = new CommentRecyclerAdapter(this, maps) {
//            @Override
//            protected int getMultipleHolderType(Object dataItem, int position) {
//                if (position % 2 == 0) {
//                    return R.layout.item_spec_input;
//                } else {
//                    return R.layout.item_spec_input2;
//                }
//            }
//        };
////        mAdapter = new TestAdapter(TestActivity.this, R.layout.item_spec_input, TestHolder.class);
//        recyclerView.setAdapter(mAdapter);
////解决数据加载不完的问题
//        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.setHasFixedSize(true);
////解决数据加载完成后, 没有停留在顶部的问题
//        recyclerView.setFocusable(false);
//    }
//
//    public void sure(View view) {
//        List<String> data = new ArrayList<>();
//        data.add("颜色");
//        data.add("尺寸");
//        data.add("大小");
//        data.add("高度");
//        data.add("宽度");
//        data.add("颜色");
//        data.add("尺寸");
//        data.add("大小");
//        data.add("高度");
//        data.add("宽度");
//        data.add("广度");
//        data.add("尺寸");
//        data.add("大小");
//        data.add("高度");
//        data.add("宽度");
//        data.add("高度");
//        data.add("宽度");
//        data.add("广度");
//        data.add("尺寸");
//        data.add("大小");
//        data.add("高度");
//        data.add("宽度");
//        data.add("高度");
//        data.add("宽度");
//        data.add("广度");
//        data.add("尺寸");
//        data.add("大小");
//        data.add("高度");
//        data.add("宽度");
//        data.add("广度");
//        data.add("颜色");
//        data.add("尺寸");
//        data.add("大小");
//        data.add("高度");
//        data.add("宽度");
//        data.add("广度");
//        data.add("尺寸");
//        data.add("大小");
//        data.add("高度");
//        data.add("宽度");
//        data.add("高度");
//        data.add("宽度");
//        data.add("广度");
//        data.add("尺寸");
//        data.add("大小");
//        data.add("高度");
//        data.add("宽度");
//        data.add("高度");
//        data.add("宽度");
//        data.add("广度");
//        data.add("尺寸");
//        data.add("大小");
//        data.add("高度");
//        data.add("宽度");
//        data.add("广度");
//        data.add("颜色");
//        data.add("尺寸");
//        data.add("大小");
//        data.add("高度");
//        data.add("宽度");
//        data.add("广度");
//        data.add("尺寸");
//        data.add("大小");
//        data.add("高度");
//        data.add("宽度");
//        data.add("高度");
//        data.add("宽度");
//        data.add("广度");
//        data.add("尺寸");
//        data.add("大小");
//        data.add("高度");
//        data.add("宽度");
//        data.add("高度");
//        data.add("宽度");
//        data.add("广度");
//        data.add("尺寸");
//        data.add("大小");
//        data.add("高度");
//        data.add("宽度");
//        data.add("广度");
//        data.add("广度");
//        data.add("尺寸");
//        data.add("大小");
//        data.add("高度");
//        data.add("宽度");
//        data.add("高度");
//        data.add("宽度");
//        data.add("广度");
//        data.add("尺寸");
//        data.add("大小");
//        data.add("高度");
//        data.add("宽度");
//        data.add("高度");
//        data.add("宽度");
//        data.add("广度");
//        data.add("尺寸");
//        data.add("大小");
//        data.add("高度");
//        data.add("宽度");
//        data.add("广度");
//        mAdapter.setData(data);
//    }
//
//    public void addData(View view) {
//        new Thread(() -> {
//            try {
//                sleep(100);
////                TLog.bean("TestActivity", mAdapter.getInputData().toString());
////                Log.e("TestActivity", mAdapter.getInputData().toString());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
////        mAdapter.getInputData();
//    }
}

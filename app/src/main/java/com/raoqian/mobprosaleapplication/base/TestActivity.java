package com.raoqian.mobprosaleapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.raoqian.mobprosaleapplication.R;
import com.raoqian.mobprosaleapplication.base.recycler.BaseRecyclerAdapter;
import com.raoqian.mobprosaleapplication.base.recycler.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2018/5/26
 */

public class TestActivity extends BaseActivity {

    RecyclerView recyclerView;
    BaseRecyclerAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initRecycler1();
    }

    private void initRecycler1() {
        recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        SparseArray<Class<? extends BaseViewHolder>> maps = new SparseArray<>();


        maps.put(R.layout.item_spec_input, TestHolder.class);
        maps.put(R.layout.item_spec_input2, TestHolder2.class);
        mAdapter = new BaseRecyclerAdapter(this, maps) {
            @Override
            protected int getMultipleHolderType(Object dataItem, int position) {
                if (position % 2 == 0) {
                    return R.layout.item_spec_input;
                } else {
                    return R.layout.item_spec_input2;
                }
            }
        };
//        mAdapter = new TestAdapter(TestActivity.this, R.layout.item_spec_input, TestHolder.class);
        recyclerView.setAdapter(mAdapter);
//解决数据加载不完的问题
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
//解决数据加载完成后, 没有停留在顶部的问题
        recyclerView.setFocusable(false);
    }

    public void sure(View view) {
        List<String> data = new ArrayList<>();
        data.add("颜色");
        data.add("尺寸");
        data.add("大小");
        data.add("高度");
        data.add("宽度");
        data.add("颜色");
        data.add("尺寸");
        data.add("大小");
        data.add("高度");
        data.add("宽度");
        data.add("广度");
        data.add("尺寸");
        data.add("大小");
        data.add("高度");
        data.add("宽度");
        data.add("高度");
        data.add("宽度");
        data.add("广度");
        data.add("尺寸");
        data.add("大小");
        data.add("高度");
        data.add("宽度");
        data.add("高度");
        data.add("宽度");
        data.add("广度");
        data.add("尺寸");
        data.add("大小");
        data.add("高度");
        data.add("宽度");
        data.add("广度");
        data.add("颜色");
        data.add("尺寸");
        data.add("大小");
        data.add("高度");
        data.add("宽度");
        data.add("广度");
        data.add("尺寸");
        data.add("大小");
        data.add("高度");
        data.add("宽度");
        data.add("高度");
        data.add("宽度");
        data.add("广度");
        data.add("尺寸");
        data.add("大小");
        data.add("高度");
        data.add("宽度");
        data.add("高度");
        data.add("宽度");
        data.add("广度");
        data.add("尺寸");
        data.add("大小");
        data.add("高度");
        data.add("宽度");
        data.add("广度");
        data.add("颜色");
        data.add("尺寸");
        data.add("大小");
        data.add("高度");
        data.add("宽度");
        data.add("广度");
        data.add("尺寸");
        data.add("大小");
        data.add("高度");
        data.add("宽度");
        data.add("高度");
        data.add("宽度");
        data.add("广度");
        data.add("尺寸");
        data.add("大小");
        data.add("高度");
        data.add("宽度");
        data.add("高度");
        data.add("宽度");
        data.add("广度");
        data.add("尺寸");
        data.add("大小");
        data.add("高度");
        data.add("宽度");
        data.add("广度");
        data.add("广度");
        data.add("尺寸");
        data.add("大小");
        data.add("高度");
        data.add("宽度");
        data.add("高度");
        data.add("宽度");
        data.add("广度");
        data.add("尺寸");
        data.add("大小");
        data.add("高度");
        data.add("宽度");
        data.add("高度");
        data.add("宽度");
        data.add("广度");
        data.add("尺寸");
        data.add("大小");
        data.add("高度");
        data.add("宽度");
        data.add("广度");
        mAdapter.setData(data);
    }

    public void addData(View view) {
        new Thread(() -> {
            try {
                sleep(100);
//                TLog.bean("TestActivity", mAdapter.getInputData().toString());
//                Log.e("TestActivity", mAdapter.getInputData().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
//        mAdapter.getInputData();
    }
}

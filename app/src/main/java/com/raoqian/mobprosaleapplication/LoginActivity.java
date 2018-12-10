package com.raoqian.mobprosaleapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        RecyclerView recycler = findViewById(R.id.recycler);
        SizeAdapter mSizeAdapter = new SizeAdapter(LoginActivity.this);
        GridLayoutManager lin = new GridLayoutManager(LoginActivity.this, 5) {
            @Override
            public boolean canScrollHorizontally() {
                return true;
            }

            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };
        recycler.setLayoutManager(lin);
        recycler.setAdapter(mSizeAdapter);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add("" + i);
        }
        mSizeAdapter.setData(data);

    }
}


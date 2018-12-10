package com.raoqian.mobprosaleapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by rq on 2018/12/10.
 */

public class HorRecyclerView extends RecyclerView {
    public HorRecyclerView(Context context) {
        super(context);
    }

    public HorRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HorRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
    }
}

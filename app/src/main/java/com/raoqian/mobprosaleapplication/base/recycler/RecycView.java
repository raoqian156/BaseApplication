package com.raoqian.mobprosaleapplication.base.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by rq on 2018/10/8.
 */

public class RecycView extends RecyclerView implements View.OnTouchListener, CommentRecyclerAdapter.OnAttachedToBottomListener {
    public RecycView(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public RecycView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
    }

    public RecycView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnTouchListener(this);
        setOnScrollListener(this);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter instanceof CommentRecyclerAdapter) {
            ((CommentRecyclerAdapter) adapter).setOnAttachedToBottomListener(this);
        }
    }

    private void setOnScrollListener(RecycView recycView) {
        Log.e("RecycView", "setOnScrollListener: ");
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.e("RecycView", "onTouch: (" + motionEvent.getX() + "," + motionEvent.getY() + ")");
        return super.onTouchEvent(motionEvent);
    }

    @Override
    public void onAttachedToBottom(int position) {
        int lastHeight = getLayoutManager().findViewByPosition(position).getHeight();
        Log.e("RecycView", "onAttachedToBottom: lastHeight = " + lastHeight);

    }
}

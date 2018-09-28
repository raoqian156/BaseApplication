package com.raoqian.mobprosaleapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2018/5/29.
 */

public class CustomScrollView extends ScrollView {

    private OnScrollListener listener;

    public void setOnScrollListener(OnScrollListener listener) {
        this.listener = listener;
    }

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //设置接口
    public interface OnScrollListener {
        void onScroll(int scrollY);
    }

    //重写原生onScrollChanged方法，将参数传递给接口，由接口传递出去
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (listener != null) {
            //这里我只传了垂直滑动的距离
            listener.onScroll(t);
        }
    }
}

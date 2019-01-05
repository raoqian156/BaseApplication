package com.raoqian.mobprosaleapplication.calendar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by raoqian on 2018/12/26.
 */

public class CalendarView extends ViewGroup {
    int hang = 5;//行数
    int lie = 7;//列数
    View[][] childrens = new View[hang][lie];
    int mScreenWidth = 0;
    int mScreenHeight = 0;
    int perLine = 1;

    public CalendarView(Context context) {
        this(context, null, 0);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        this.mScreenWidth = point.x;
        this.mScreenHeight = point.y;
//        int height = wm.getDefaultDisplay().getHeight();
        this.mScroller = new Scroller(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < lie; j++) {
                TextView item = new TextView(getContext());
                item.setTextColor(Color.BLACK);
                item.setGravity(Gravity.CENTER);
                item.setBackgroundColor(Color.WHITE);
//                if (i % 2 == 0) {
//                    item.setBackgroundColor(Color.RED);
//                } else {
//                    item.setBackgroundColor(Color.GREEN);
//                }
                item.setText("(" + i + "," + j + ")");
                childrens[i][j] = item;
                addView(childrens[i][j]);
            }
        }
    }

    Scroller mScroller;

    //对子视图进行绘制
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int pWidth = 0;
    private int pHeight = 0;
    int itemHeight = 0;
    int oldParentHeight = 0;

    //确定子视图位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        pWidth = r - l;
        pHeight = b - t;
        int itemWidth = (r - l) / lie;
        int pHeight = (b - t);
        int sHeight = pHeight / hang;
        if (itemHeight == 0) {
            oldParentHeight = b - t;
            itemHeight = (b - t) / hang;
        }
        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < lie; j++) {
                float rate = 0;//-0.25F * itemHeight * sHeight + 1 + 0.25F * itemHeight;
                float p = (i * 1F / hang) * itemHeight;
                int offsetY = (int) ((i / 5F) * itemHeight - rate * p);
                int itemTop = t + sHeight * i - offsetY;
                if (j == 0) {
                    Log.e("CalendarView", i +
                            "  pHeight -> " + pHeight + "  itemHeight = " + itemHeight +
                            ".(pHeight - itemHeight) = " + ((pHeight - itemHeight)) + "  itemTop = " + itemTop);
                }
                childrens[i][j].layout(
                        l + itemWidth * j,
                        itemTop,
                        l + itemWidth * (j + 1),
                        t + sHeight * i + itemHeight)
                ;
            }
        }
    }

    private int lastX = 0;
    private int lastY = 0;
    private int mEnd = 0;
    int test = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        int x = (int) event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 记录触摸点坐标
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                // 计算偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                // 在当前left、top、right、bottom的基础上加上偏移量
                int newHeight = getBottom() - getTop() + offsetY;
                if (newHeight > itemHeight && newHeight < oldParentHeight) {
                    layout(getLeft(),
                            getTop(),
                            getRight(),
                            getBottom() + offsetY);
                }
                lastY = y;
                break;
        }
//        postInvalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }
}

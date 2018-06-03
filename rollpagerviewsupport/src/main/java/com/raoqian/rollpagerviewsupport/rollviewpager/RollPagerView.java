package com.raoqian.rollpagerviewsupport.rollviewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.raoqian.rollpagerviewsupport.R;
import com.raoqian.rollpagerviewsupport.rollviewpager.adapter.HintViewDelegate;
import com.raoqian.rollpagerviewsupport.rollviewpager.adapter.ImageAdapter;
import com.raoqian.rollpagerviewsupport.rollviewpager.adapter.LoopPagerAdapter;
import com.raoqian.rollpagerviewsupport.rollviewpager.hintview.BaseHintView;
import com.raoqian.rollpagerviewsupport.rollviewpager.hintview.ColorPointHintView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * 支持轮播和提示的的viewpager
 */
public class RollPagerView extends RelativeLayout implements OnPageChangeListener {

    private final static int ACTION_SCROLL_TO_NEXT_PAGER = 0;
    private final static int ACTION_SCROLL_TO_LAST_PAGER = -1;
    private final static int ACTION_SCROLL_TO_INDEX_PAGER = 1;
    private ViewPager mViewPager;
    private PagerAdapter mAdapter;
    private OnItemClickListener mOnItemClickListener;
    private GestureDetector mGestureDetector;
    private int mCurrentPosition = 0;
    private long mRecentTouchTime;
    //自动播放间隔时间
    private int interval;
    //hint位置
    private int gravity;
    private int location;
    //hint颜色
    private int color;
    //hint透明度
    private int alpha;

    private int paddingLeft;
    private int paddingTop;
    private int paddingRight;
    private int paddingBottom;
    private boolean isAuto;

    private int selectedDotRes = 0;

    private View mHintView;
    private Timer timer;

    public RollPagerView(Context context) {
        this(context, null, 0);
    }

    public RollPagerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RollPagerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs);
    }

    /**
     * 读取提示形式  和   提示位置   和    播放延迟
     *
     * @param context
     * @param attrs
     */
    private void initView(Context context, AttributeSet attrs) {
        if (mViewPager != null) {
            removeView(mViewPager);
        }

        TypedArray type = getContext().obtainStyledAttributes(attrs, R.styleable.RollViewPager);
        gravity = type.getInteger(R.styleable.RollViewPager_hint_gravity, 1);
        location = type.getInteger(R.styleable.RollViewPager_hint_gravity, 19);
        isAuto = type.getBoolean(R.styleable.RollViewPager_content_autoPlay, false);
        interval = type.getInt(R.styleable.RollViewPager_content_autoPlayInterval, 3000);
        color = type.getColor(R.styleable.RollViewPager_hint_color, Color.BLACK);
        alpha = type.getInt(R.styleable.RollViewPager_hint_alpha, 0);
        selectedDotRes = type.getResourceId(R.styleable.RollViewPager_selected_dot_res, 0);
        paddingLeft = (int) type.getDimension(R.styleable.RollViewPager_hint_marginBottom, 0);
        paddingRight = (int) type.getDimension(R.styleable.RollViewPager_hint_marginRight, 0);
        paddingTop = (int) type.getDimension(R.styleable.RollViewPager_hint_marginTop, 0);
        paddingBottom = (int) type.getDimension(R.styleable.RollViewPager_hint_marginBottom, Util.dip2px(getContext(), 0));
        mViewPager = new ViewPager(getContext());
        mViewPager.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(mViewPager);
        type.recycle();
        initHint(new ColorPointHintView(getContext(), Color.parseColor("#E3AC42"), Color.parseColor("#88ffffff")));
        //手势处理
        mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                if (mOnItemClickListener != null) {
                    if (mAdapter instanceof LoopPagerAdapter) {//原谅我写了这么丑的代码
                        mOnItemClickListener.onItemClick(mViewPager.getCurrentItem() % ((LoopPagerAdapter) mAdapter).getRealCount());
                    } else {
                        mOnItemClickListener.onItemClick(mViewPager.getCurrentItem());
                    }
                }
                return super.onSingleTapUp(e);
            }
        });
//        if (isAuto) {
//            setPlayDelay(interval);
//        }
//        mHintView = new CustomHintView(context, selectedDotRes);
        Log.e("RollPagerView", "selectedDotRes = " + selectedDotRes);
        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.e("RollPagerView", "onGlobalLayout.getHeight()) = " + getHeight());
                Log.e("RollPagerView", "onGlobalLayout.getWidth()) = " + getWidth());
                Log.e("RollPagerView", "onGlobalLayout.getMeasuredHeight()) = " + getMeasuredHeight());
                Log.e("RollPagerView", "onGlobalLayout.getMeasuredWidth()) = " + getMeasuredWidth());
                loadHintView();
            }
        });
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int cur;
                switch (msg.what) {
                    case ACTION_SCROLL_TO_NEXT_PAGER:
                        cur = mViewPager.getCurrentItem() + 1;
                        break;
                    case ACTION_SCROLL_TO_LAST_PAGER:
                        cur = mViewPager.getCurrentItem() - 1;
                        break;
                    case ACTION_SCROLL_TO_INDEX_PAGER:
                        cur = mViewPager.getCurrentItem();
                        cur = cur / 3 * 3 + msg.arg1;
                        if (cur >= mViewPager.getAdapter().getCount()) {
                            cur = 0;
                        }
                        break;
                    default:
                        cur = 0;
                        break;
                }
                if (cur >= mViewPager.getAdapter().getCount()) {
                    cur = 0;
                }
                Log.e("RollPagerView", " mViewPager.getCurrentItem() = " + mViewPager.getCurrentItem());
                Log.e("RollPagerView", "target = " + cur);
                mViewPager.setCurrentItem(cur);
//        rollPagerView.mHintViewDelegate.setCurrentPosition(cur, (HintView) rollPagerView.mHintView);
//        if (rollPagerView.mAdapter.getCount() <= 1) rollPagerView.stopPlay();

            }
        };
    }


    public void setData(List<String> name, int[] debugImgs) {
        ImageAdapter mLoopAdapter = new ImageAdapter(this, getContext());
        setAdapter(mLoopAdapter);
        Log.e("RollPagerView", "setData.selectedDotRes = " + selectedDotRes);
        mHintView = new BaseHintView(getContext(), name, selectedDotRes, R.layout.view_top_pic_text, R.id.test, R.id.img1);
        setHintView((HintView) mHintView);//new IconHintView(getContext(), R.drawable.point_focus, R.drawable.point_normal));//
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.e("HomeActivity", "click p = " + position);
            }
        });
        mLoopAdapter.setImgs(debugImgs);//realPics
    }


    private HintViewDelegate mHintViewDelegate = new HintViewDelegate() {
        @Override
        public void setCurrentPosition(int position, HintView hintView) {
            if (hintView != null)
                hintView.setCurrent(position);
        }

        @Override
        public void initView(int length, int gravity, HintView hintView) {
            if (hintView != null)
                hintView.initView(length, gravity);
        }
    };


    public void setCurrentItem(int position) {
        Log.e("RollPagerView", "setCurrentItem.position = " + position);
        this.mCurrentPosition = position;
        Message message = new Message();
        message.what = ACTION_SCROLL_TO_INDEX_PAGER;
        message.arg1 = position;
        mHandler.handleMessage(message);
    }

    private Handler mHandler;

//    private class WeakTimerTask extends TimerTask {
//
//        @Override
//        public void run() {
//            if (mViewPager != null) {
//                if (mViewPager.isShown() && System.currentTimeMillis() - mRecentTouchTime > interval) {
//                    mHandler.sendEmptyMessage(0);
//                }
//            } else {
//                cancel();
//            }
//        }
//    }

    /**
     * 开始播放
     * 仅当view正在显示 且 触摸等待时间过后 播放
     */
    private void startPlay() {
        if (interval <= 0 || mAdapter == null || mAdapter.getCount() <= 1) {
            return;
        }
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        //用一个timer定时设置当前项为下一项
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mViewPager != null) {
                    if (mViewPager.isShown() && System.currentTimeMillis() - mRecentTouchTime > interval) {
                        mHandler.sendEmptyMessage(ACTION_SCROLL_TO_NEXT_PAGER);
                    }
                } else {
                    cancel();
                }
            }
        }, 0, interval);
        Log.e("RollPagerView.LINE", "243");
    }

    private void stopPlay() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }


    public void setHintViewDelegate(HintViewDelegate delegate) {
        this.mHintViewDelegate = delegate;
    }


    private void initHint(HintView hintview) {
        if (mHintView != null) {
            removeView(mHintView);
        }

        if (hintview == null) {
            return;
        }

        mHintView = (View) hintview;
        loadHintView();
    }


    public int getLocationLT() {
        int lt = 9;
        for (int com = location, res = location % 10; com > 0; com = com / 10, res = com % 10) {
            lt = Math.min(lt, res);
        }
        return lt;
    }

    public int getLocationRB() {
        int lt = 1;
        for (int com = location, res = location % 10; com > 0; com = com / 10, res = com % 10) {
            lt = Math.max(lt, res);
        }
        return lt;
    }

    private boolean hasLoadHint = false;

    /**
     * 加载hintview的容器
     */
    private void loadHintView() {
        if (mHintView == null) {
            return;
        }
        if (getMeasuredWidth() == 0) {
            return;
        }
        if (hasLoadHint) {
            return;
        }
        this.hasLoadHint = true;

        int locationLT = getLocationLT();
        int locationRB = getLocationRB();
        int l = (int) (((locationLT - 1) % 3) / 3L * getMeasuredWidth());
        int t = (int) (((locationLT - 1) / 3) / 3L * getMeasuredHeight());
        int r = (int) (3 - ((locationRB - 1) % 3) / 3L * getMeasuredWidth());
        int b = (int) (3 - ((locationRB - 1) / 3) / 3L * getMeasuredHeight());
        addView(mHintView);
        Log.e("RollPagerView", "VPW:" + getMeasuredWidth() + "VPH:" + getMeasuredHeight() + " ->  " + l + "-" + t + "-" + r + "-" + b);
//        mHintView.setPadding(paddingLeft + l, paddingTop + t, paddingRight + r, paddingBottom + b);
        mHintView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        (mHintView).setLayoutParams(lp);

        GradientDrawable gd = new GradientDrawable();
        gd.setColor(color);
        gd.setAlpha(alpha);
        mHintView.setBackgroundDrawable(gd);

        mHintViewDelegate.initView(mAdapter == null ? 0 : mAdapter.getCount(), gravity, (HintView) mHintView);
    }


    @Override
    public void onScreenStateChanged(int screenState) {//系统亮屏 0 - 黑屏, 1 - 亮锁
        super.onScreenStateChanged(screenState);
        if (SCREEN_STATE_OFF == screenState) {
            stopPlay();
        } else if (SCREEN_STATE_ON == screenState) {
            startPlay();
        }
    }

    /**
     * 设置viewager滑动动画持续时间
     *
     * @param during
     */
    public void setAnimationDurtion(final int during) {
        try {
            // viePager平移动画事件
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            Scroller mScroller = new Scroller(getContext(),
                    // 动画效果与ViewPager的一致
                    new Interpolator() {
                        public float getInterpolation(float t) {
                            t -= 1.0f;
                            return t * t * t * t * t + 1.0f;
                        }
                    }) {

                @Override
                public void startScroll(int startX, int startY, int dx,
                                        int dy, int interval) {
                    // 如果手工滚动,则加速滚动
                    if (System.currentTimeMillis() - mRecentTouchTime > interval) {
                        interval = during;
                    } else {
                        interval /= 2;
                    }
                    super.startScroll(startX, startY, dx, dy, interval);
                }

                @Override
                public void startScroll(int startX, int startY, int dx,
                                        int dy) {
                    super.startScroll(startX, startY, dx, dy, during);
                }
            };
            mField.set(mViewPager, mScroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setPlayDelay(int interval) {
        this.interval = interval;
        startPlay();
    }

    public boolean isPlaying() {
        return timer != null;
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * 设置提示view的位置
     */
    public void setHintPadding(int left, int top, int right, int bottom) {
        paddingLeft = left;
        paddingTop = top;
        paddingRight = right;
        paddingBottom = bottom;
        mHintView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    /**
     * 设置提示view的透明度
     *
     * @param alpha 0为全透明  255为实心
     */
    public void setHintAlpha(int alpha) {
        this.alpha = alpha;
        initHint((HintView) mHintView);
    }

    /**
     * 支持自定义hintview
     * 只需new一个实现HintView的View传进来
     * 会自动将你的view添加到本View里面。重新设置LayoutParams。
     *
     * @param hintview
     */
    public void setHintView(HintView hintview) {
        if (mHintView != null) {
            removeView(mHintView);
        }
        this.mHintView = (View) hintview;
        if (hintview != null) {
            initHint(hintview);
        }
    }

    /**
     * 取真正的Viewpager
     *
     * @return
     */
    public ViewPager getViewPager() {
        return mViewPager;
    }

    /**
     * 设置Adapter
     *
     * @param adapter
     */
    public void setAdapter(PagerAdapter adapter) {
        adapter.registerDataSetObserver(new JPagerObserver());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
        mAdapter = adapter;
        dataSetChanged();
    }

    /**
     * 用来实现adapter的notifyDataSetChanged通知HintView变化
     */
    private class JPagerObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            dataSetChanged();
        }

        @Override
        public void onInvalidated() {
            dataSetChanged();
        }
    }

    private void dataSetChanged() {
        if (mHintView != null) {
            mHintViewDelegate.initView(mAdapter.getCount(), gravity, (HintView) mHintView);
            mHintViewDelegate.setCurrentPosition(mViewPager.getCurrentItem(), (HintView) mHintView);
        }
        if (isAuto) {
            startPlay();
        }
    }

    /**
     * 为了实现触摸时和过后一定时间内不滑动,这里拦截
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mRecentTouchTime = System.currentTimeMillis();
        mGestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        for (OnPageChangeListener pageChangeListener : pageChangeListeners) {
            pageChangeListener.onPageScrollStateChanged(arg0);
        }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        for (OnPageChangeListener pageChangeListener : pageChangeListeners) {
            pageChangeListener.onPageScrolled(arg0, arg1, arg2);
        }
    }

    @Override
    public void onPageSelected(int arg0) {
        mHintViewDelegate.setCurrentPosition(arg0, (HintView) mHintView);
        mCurrentPosition = arg0 % getChildCount();
        for (OnPageChangeListener pageChangeListener : pageChangeListeners) {
            pageChangeListener.onPageSelected(mCurrentPosition);
        }
    }


    List<OnPageChangeListener> pageChangeListeners = new ArrayList<>();

    public void addOnPageChangeListener(@NonNull OnPageChangeListener listener) {
        if (!pageChangeListeners.contains(listener)) {
            pageChangeListeners.add(listener);
        }
    }

    public boolean removeOnPageChangeListener(@NonNull OnPageChangeListener listener) {
        return pageChangeListeners.remove(listener);
    }
}

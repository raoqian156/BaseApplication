package com.raoqian.mobprosaleapplication.ui;

import android.graphics.Color;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.raoqian.mobprosaleapplication.CustomScrollView;
import com.raoqian.mobprosaleapplication.R;
import com.raoqian.mobprosaleapplication.base.BaseFragment;
import com.raoqian.mobprosaleapplication.utils.StatusBarUtil;
import com.raoqian.mobprosaleapplication.utils.TLog;
import com.raoqian.mobprosaleapplication.zhujie.ViewById;
import com.raoqian.rollpagerviewsupport.rollviewpager.RollPagerView;

import java.util.ArrayList;
import java.util.List;

import static com.raoqian.mobprosaleapplication.base.BaseActivity.MAIN_COLOR_BLUE;
import static com.raoqian.mobprosaleapplication.base.BaseActivity.MAIN_COLOR_GREEN;
import static com.raoqian.mobprosaleapplication.base.BaseActivity.MAIN_COLOR_RED;

/**
 * Created by Administrator on 2018/5/29.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    //    @ViewById(R.id.text)
//    EditText textView;
//    @ViewById(R.id.show)
//    TextView showView;
//    @ViewById(R.id.gif)
    ImageView gifShow;
    @ViewById(R.id.scrollView)
    CustomScrollView scrollView;
    @ViewById(R.id.inputPart)
    View inputPart;
    @ViewById(R.id.input)
    EditText mInput;
    RollPagerView pagerView;


    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_home;
    }

    String testUrl = "http://193.112.162.47/static/img/gggif.gif";

    @Override
    protected void initView(View view) {
//        StatusBarUtil.setTranslucentForImageViewInFragment(getActivity(), view);
//        ViewUtils.inject(this, view);
        view.findViewById(R.id.click1).setOnClickListener(this);
        view.findViewById(R.id.click2).setOnClickListener(this);
        view.findViewById(R.id.click3).setOnClickListener(this);
        view.findViewById(R.id.click4).setOnClickListener(this);
        scrollView = view.findViewById(R.id.scrollView);
        inputPart = view.findViewById(R.id.inputPart);
        mInput = view.findViewById(R.id.input);
        gifShow = view.findViewById(R.id.gif);
        String save = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        Log.e("HomeFragment", "save = " + save);
//        ApkDownLoadUtil mUtil = new ApkDownLoadUtil(getContext(), null, save) {
//            @Override
//            protected Uri getApkUrlInAndroid_N(File file) {
//                return null;
//            }
//
//            @Override
//            public void installApk() {
//                Log.e("HomeFragment", "url = " + getApkUrl());
//            }
//        };
//        mUtil.downloadApk(File.separator + "abc.gif", testUrl);
//        gifShow.setImageURI(Uri.parse(testUrl));
//        ImageLoaderUtils.show(testUrl, gifShow, 0);
        TLog.error("HomeFragment", "initView.testUrl = " + testUrl);
        scrollView.setOnScrollListener(scrollY -> addAlpha(scrollY));


//        Movie mMovie = getResources().getMovie(R.raw.show);
//        Log.e("HomeFragment", "   mMovie.duration() = " + mMovie.duration());
//        mMovie.duration();


        setNeedMoveToBottomViewId(view, R.id.inputPart);
        getViewStatus().setVisibility(View.GONE);
        pagerView = view.findViewById(R.id.image_show_roll);
        List<String> name = new ArrayList<>();
        name.add("东方能源石家庄公司石车光伏电站");
        name.add("利德浆料光伏电站");
        name.add("天津时代新材1.5MW光伏电站");
        int[] debugImgs = new int[3];
        debugImgs[0] = R.mipmap.icon_show_shijiazhuang;
        debugImgs[1] = R.mipmap.icon_show_lide;
        debugImgs[2] = R.mipmap.icon_tianjing;
        pagerView.setData(name, debugImgs);
    }

    private void addAlpha(float scrollY) {
        Log.e("HomeFragment", "scrollY = " + scrollY);
        runOnUiThread(() -> {
            float target = scrollY / 200L - 1;
            Log.e("HomeFragment", "target = " + target);
            if (target > 0) {
                getViewStatus().setVisibility(View.VISIBLE);
                if (target <= 1) {
                    StatusBarUtil.setTranslucentForCoordinatorLayout(getActivity(), (int) (target * 255));
                    inputPart.setBackgroundColor(Color.argb((int) (target * 255), MAIN_COLOR_RED, MAIN_COLOR_GREEN, MAIN_COLOR_BLUE));
                    mInput.setBackgroundResource(R.drawable.input_stock_white);
                    getViewStatus().setAlpha(target);
                } else {
                    StatusBarUtil.setTranslucentForCoordinatorLayout(getActivity(), 255);
                    inputPart.setBackgroundColor(Color.argb(255, MAIN_COLOR_RED, MAIN_COLOR_GREEN, MAIN_COLOR_BLUE));
                    getViewStatus().setAlpha(1.0F);

                    mInput.setBackgroundResource(R.drawable.input_stock_gray);
                }
            } else {
                StatusBarUtil.setTranslucentForCoordinatorLayout(getActivity(), 0);
                inputPart.setBackground(null);
                getViewStatus().setVisibility(View.GONE);
            }
        });
    }

    float alpha = 500;

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.click1) {
            pagerView.setCurrentItem(0);
        }
        if (v.getId() == R.id.click2) {
            pagerView.setCurrentItem(1);
        }
        if (v.getId() == R.id.click3) {
            pagerView.setCurrentItem(2);
        }
        if (v.getId() == R.id.click4) {
            pagerView.setCurrentItem(3);
        }
    }
}

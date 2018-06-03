package com.raoqian.mobprosaleapplication.ui;

import android.view.View;

import com.raoqian.mobprosaleapplication.R;
import com.raoqian.mobprosaleapplication.base.BaseFragment;


/**
 * Created by Administrator on 2018/5/29.
 */

public class ProfileFragment extends BaseFragment {

    @Override
    protected int onGetLayoutId() {
        return R.layout.activity_profile;
    }

    @Override
    protected void initView(View view) {
        setNeedMoveToBottomViewId(view, R.id.title, R.id.title2);
    }
}

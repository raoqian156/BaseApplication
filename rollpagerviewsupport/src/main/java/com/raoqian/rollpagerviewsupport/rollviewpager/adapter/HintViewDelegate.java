package com.raoqian.rollpagerviewsupport.rollviewpager.adapter;

import com.raoqian.rollpagerviewsupport.rollviewpager.HintView;

/**
 * Created by Administrator on 2018/6/3.
 */

public interface HintViewDelegate {
    void setCurrentPosition(int position, HintView hintView);

    void initView(int length, int gravity, HintView hintView);
}

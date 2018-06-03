package com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject;

import android.widget.ImageView;

/**
 * Created by Administrator on 2018/5/15.
 */

public interface ImageLoaderProcessor {
    void displayImageOnView(String path, ImageView view, int defaultRes);
}

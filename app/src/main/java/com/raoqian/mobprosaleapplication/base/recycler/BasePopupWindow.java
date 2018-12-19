package com.raoqian.mobprosaleapplication.base.recycler;

import android.util.Log;
import android.widget.PopupWindow;

/**
 * Created by rq on 2018/12/12.
 */

public class BasePopupWindow extends PopupWindow {
    public BasePopupWindow() {
        super();
        Log.e("BasePopupWindow", this.getClass().getName());
    }
}

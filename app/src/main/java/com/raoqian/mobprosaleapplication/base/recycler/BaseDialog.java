package com.raoqian.mobprosaleapplication.base.recycler;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.util.Log;

/**
 * Created by rq on 2018/12/12
 * Base基类
 */

public class BaseDialog extends Dialog {
    public BaseDialog(@NonNull Context context) {
        this(context, 0);
    }

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        Log.e("BaseDialog", "BaseDialog: " + this.getClass().getName());
    }
}

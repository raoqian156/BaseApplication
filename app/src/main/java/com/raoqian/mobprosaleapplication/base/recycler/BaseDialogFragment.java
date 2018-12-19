package com.raoqian.mobprosaleapplication.base.recycler;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by rq on 2018/12/12.
 */

public class BaseDialogFragment extends DialogFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("BaseDialogFragment", this.getClass().getName());
    }
}


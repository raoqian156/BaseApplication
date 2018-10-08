package com.raoqian.mobprosaleapplication.base;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.raoqian.mobprosaleapplication.base.recycler.BaseRecyclerAdapter;
import com.raoqian.mobprosaleapplication.base.recycler.BaseViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/5/28.
 */

public class TestAdapter extends BaseRecyclerAdapter<String, TestHolder> implements View.OnFocusChangeListener {

    public TestAdapter(Context context, int itemLayoutId, Class<? extends BaseViewHolder> baseViewHolderClass) {
        super(context, itemLayoutId, baseViewHolderClass);
    }

    List<List<String>> inputList = new ArrayList<>();

    List<List<String>> getInputData() {
        return inputList;
    }

    private void setSpecContent(int position, String content) {
        List<String> da = getListFromInput(content);
        inputList.set(position, da);
    }

    public static List<String> getListFromInput(String content) {
        List<String> res = new ArrayList<>();
        if (content != null && content.length() > 0) {
            String[] param = content.split(",");
            res.addAll(Arrays.asList(param));
        }
        return res;
    }

//    private int currentFocusOn = -1;

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int position = (int) v.getTag();
        if (!hasFocus) {
            String content = ((EditText) v).getText().toString();
            Log.e("SpecAdapter", "input over -> " + position + ":" + content);
            setSpecContent(position, content);
        }
//        else {
//            this.currentFocusOn = position;
//        }
    }
}

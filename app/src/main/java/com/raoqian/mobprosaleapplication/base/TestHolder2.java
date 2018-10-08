package com.raoqian.mobprosaleapplication.base;

import android.view.View;
import android.widget.CheckBox;

import com.raoqian.mobprosaleapplication.R;
import com.raoqian.mobprosaleapplication.base.recycler.BaseViewHolder;
import com.raoqian.mobprosaleapplication.base.recycler.OnContentKeeper;

/**
 * Created by rq on 2018/9/28.
 */

public class TestHolder2 extends BaseViewHolder implements OnContentKeeper {

    public TestHolder2(View itemView) {
        super(itemView);
    }

    @Override
    public int inflateLayoutId() {
        return R.layout.item_spec_input2;
    }

    @Override
    public void fillData(Object o) {
        setTextToView("  " + getPosition() + "   ", R.id.remind);
    }

    @Override
    public Object getSave(int saveContentId) {
        if (saveContentId == R.id.input) {
            return getTextFromView(R.id.input);
        } else if (saveContentId == R.id.check) {
            return ((CheckBox) getView(R.id.check)).isChecked();
        }
        return null;
    }

    @Override
    public void onRelease(Object o, int saveContentId) {
        if (saveContentId == R.id.input) {
            if (o == null) {
                setTextToView("", R.id.input);
            } else {
                setTextToView((String) o, R.id.input);
            }
        } else if (saveContentId == R.id.check) {
            if (o == null) {
                ((CheckBox) getView(R.id.check)).setChecked(false);
            } else {
                ((CheckBox) getView(R.id.check)).setChecked((Boolean) o);
            }
        }
    }

    @Override
    public int[] getSaveViewId() {
        return new int[]{R.id.input, R.id.check};
    }

}

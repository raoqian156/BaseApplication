package com.raoqian.mobprosaleapplication;

import android.view.View;
import android.widget.CheckBox;

import com.raoqian.mobprosaleapplication.base.recycler.BaseViewHolder;
import com.raoqian.mobprosaleapplication.base.recycler.OnContentKeeper;

/**
 * Created by rq on 2018/9/28.
 */

public class ExampleHolder2 extends BaseViewHolder implements OnContentKeeper<ExampleHolder2> {
    public ExampleHolder2(View itemView) {
        super(itemView);
        setKeeper(this, R.id.input, R.id.check);
    }

    @Override
    public int inflateLayoutId() {
        return R.layout.item_spec_input;
    }

    @Override
    public Object onSave(ExampleHolder2 holder, int saveContentId) {
        if (saveContentId == R.id.input) {
            return holder.getTextFromView(R.id.input);
        } else if (saveContentId == R.id.check) {
            return ((CheckBox) holder.getView(R.id.check)).isChecked();
        }
        return null;
    }

    @Override
    public void onRelease(ExampleHolder2 holder, Object o, int saveContentId) {
        if (o == null) {
            return;
        }
        if (saveContentId == R.id.input) {
            holder.setTextToView((String) o, R.id.input);
        } else if (saveContentId == R.id.check) {
            ((CheckBox) holder.getView(R.id.check)).setChecked((Boolean) o);
        }
    }

}

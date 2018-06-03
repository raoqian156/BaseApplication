package com.raoqian.mobprosaleapplication;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.raoqian.mobprosaleapplication.bean.BaseAdapter;
import com.raoqian.mobprosaleapplication.bean.BaseHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/5/28.
 */

public class SpecAdapter extends BaseAdapter<SpecAdapter.ExampleHolder, String> implements View.OnFocusChangeListener {
    public SpecAdapter(Activity context) {
        super(context);
        setKeeper(new OnContentKeeper<ExampleHolder>() {

            @Override
            public Object onSave(ExampleHolder holder, int saveContentId) {
                if (saveContentId == R.id.input) {
                    return holder.input.getText().toString();
                } else if (saveContentId == R.id.check) {
                    return ((CheckBox) holder.getView(R.id.check)).isChecked();
                }
                return null;
            }

            @Override
            public void onRelease(ExampleHolder holder, Object o, int saveContentId) {
                if (o == null) {
                    return;
                }
                if (saveContentId == R.id.input) {
                    holder.input.setText((String) o);
                } else if (saveContentId == R.id.check) {
                    ((CheckBox) holder.getView(R.id.check)).setChecked((Boolean) o);
                }
            }
        }, R.id.input, R.id.check);
    }


    @Override
    public ExampleHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ExampleHolder(getView(R.layout.item_spec_input, parent));
    }

    @Override
    public void onBindingHolder(ExampleHolder holder, int position) {
//        holder.setTextToView(getDataItem(position), R.id.remind);
//        holder.getView(R.id.input).setTag(position);
//        holder.getView(R.id.input).setOnFocusChangeListener(this);
        holder.remind.setText(getDataItem(position));
        holder.input.setTag(position);
        holder.input.setOnFocusChangeListener(this);
        holder.setIsRecyclable(true);
//        if (position == currentFocusOn) {
////            holder.itemView.requestFocus();
//            Log.e("SpecAdapter", "onBindingHolder:position -" + position + ":" + ((EditText) holder.getView(R.id.input)).getText().toString());
////            holder.getView(R.id.input).requestFocus();
////            holder.getView(R.id.input).clearFocus();
////            this.currentFocusOn = -1;
//        }
    }

    class ExampleHolder extends BaseHolder {
        TextView remind;
        EditText input;

        public ExampleHolder(View itemView) {
            super(itemView);
            remind = itemView.findViewById(R.id.remind);
            input = itemView.findViewById(R.id.input);
            setKeepEditContent(R.id.input);
        }

        @Override
        public String toString() {
            return "ExampleHolder " + getPosition() + " {" +
                    "remind=" + remind.getText() +
                    ", input=" + input.getText() +
                    '}';
        }
    }

    @Override
    public void setData(List<String> data) {
        super.setData(data);
        while (inputList.size() <= data.size()) {
            inputList.add(new ArrayList<>());
        }
    }

    List<List<String>> inputList = new ArrayList<>();

    public void clearFocus() {
//        if (currentFocusOn > -1) {
        notifyDataSetChanged();
//        }
    }

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

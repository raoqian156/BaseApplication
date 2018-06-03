package com.raoqian.mobprosaleapplication.bean;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

public class BaseHolder extends RecyclerView.ViewHolder {
    private View mView;
    private SparseArray<View> sparseArray = new SparseArray();
    private int[] keepEditContent;

    public BaseHolder(View itemView) {
        super(itemView);
        this.mView = itemView;
    }

    public <T extends View> T getView(int id) {
        if (sparseArray.get(id) != null) {
            return (T) sparseArray.get(id);
        } else {
            sparseArray.put(id, mView.findViewById(id));
            return mView.findViewById(id);
        }
    }

    public void setTextToView(@NonNull String content, int viewId) {
        ((TextView) getView(viewId)).setText(content);
    }

    public void setKeepEditContent(int... keepEditContent) {
        this.keepEditContent = keepEditContent;
    }
}

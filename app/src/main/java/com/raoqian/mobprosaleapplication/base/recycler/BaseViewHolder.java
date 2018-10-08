package com.raoqian.mobprosaleapplication.base.recycler;

import android.support.annotation.IdRes;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by raoqian on 2018/9/21.
 */

public class BaseViewHolder<DATA> extends ViewHolder {
    public ActionPasser mActionPasser;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    private SparseArray<View> itemViews = new SparseArray<>();

    /**
     * 为了保证代码的后期维护，该方法必须重写 且必须与Adapter 初始化描述一致
     */
    public int inflateLayoutId() {//不参与诗句逻辑，只作为调试方法，方便维护
        return 0;
    }


    public void fillData(DATA data) {
    }

    public void fillObject(Object data) {
    }

    public <T extends View> T getView(int id) {
        if (itemViews.get(id) != null) {
            return (T) itemViews.get(id);
        } else {
            itemViews.put(id, itemView.findViewById(id));
            return itemView.findViewById(id);
        }
    }

    @UiThread
    protected void setTextToView(String text, @IdRes int textViewId) {
        if (getView(textViewId) != null) {
            ((TextView) getView(textViewId)).setText(text);
        } else if (itemView.findViewById(textViewId) instanceof TextView) {
            itemViews.append(textViewId, itemView.findViewById(textViewId));
            ((TextView) itemView.findViewById(textViewId)).setText(text);
        } else {
            Log.e("BaseViewHolder", "setTextToView(" + text + "," + textViewId + "): textViewId error -> " + textViewId + " is not belongs TextView");
        }
    }

    protected String getTextFromView(@IdRes int textViewId) {
        if (itemViews.get(textViewId) != null) {
            return ((TextView) getView(textViewId)).getText().toString();
        } else if (itemView.findViewById(textViewId) instanceof TextView) {
            itemViews.append(textViewId, itemView.findViewById(textViewId));
            return ((TextView) itemView.findViewById(textViewId)).getText().toString();
        } else {
            Log.e("BaseViewHolder", "getTextFromView(" + textViewId + "): textViewId error -> " + textViewId + " is not belongs TextView");
            return "";
        }
    }

    public void setPasser(ActionPasser passer) {
        this.mActionPasser = passer;
    }


    public int getMPosition() {
        return (int) itemView.getTag();
    }

    /**
     * #############################################################
     * RecyclerView 刷新保存Editext等内容会有错误
     *#############################################################
     */
}

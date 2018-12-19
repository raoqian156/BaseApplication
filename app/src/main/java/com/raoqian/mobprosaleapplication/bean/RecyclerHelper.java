package com.raoqian.mobprosaleapplication.bean;

import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rq on 2018/12/19.
 */

public class RecyclerHelper implements BaseAdapter.OnViewHolderAttachListener {

    public static RecyclerHelper init(BaseAdapter adapter) {
        return new RecyclerHelper(adapter);
    }

    private OnContentKeeper mOnContentKeeper;
    private Map<String, Object> contentCash = new HashMap<>();
    private int[] saveContentIds;

    private RecyclerHelper(BaseAdapter adapter) {
        adapter.setOnViewHolderAttachListener(this);
    }

    public void onViewDetachedFromWindow(BaseHolder holder) {
        if (saveContentIds != null && saveContentIds.length > 0) {
            for (int saveContentId : saveContentIds) {
                if (mOnContentKeeper == null) {
                    contentCash.put(holder.getLayoutPosition() + "" + saveContentId, ((EditText) holder.getView(saveContentId)).getText().toString());
                } else {
                    contentCash.put(holder.getLayoutPosition() + "" + saveContentId, mOnContentKeeper.onSave(holder, saveContentId));
                }
            }
        }
    }

    public void onViewAttachedToWindow(BaseHolder holder) {
        if (saveContentIds != null && saveContentIds.length > 0) {
            for (int saveContentId : saveContentIds) {
                if (mOnContentKeeper == null) {
                    ((TextView) holder.getView(saveContentId)).setText((String) contentCash.get(holder.getLayoutPosition() + "" + saveContentId));
                } else {
                    Object value = contentCash.get(holder.getLayoutPosition() + "" + saveContentId);
                    if (value != null) {
                        mOnContentKeeper.onRelease(holder, value, saveContentId);
                    }
                }
            }
        }
    }

    /**
     * RecyclerView 刷新不会保存Editext原有内容,需要在此确认需要保存,
     *
     * @param ids
     */
    protected void setKeepTextId(int... ids) {
        this.saveContentIds = ids;
        this.mOnContentKeeper = null;
    }

    protected void setKeeper(OnContentKeeper onContentKeeper, int... ids) {
        this.saveContentIds = ids;
        this.mOnContentKeeper = onContentKeeper;
    }


    protected interface OnContentKeeper {

        Object onSave(BaseHolder holder, int saveContentId);

        void onRelease(BaseHolder holder, Object o, int saveContentId);
    }

}

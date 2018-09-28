package com.raoqian.mobprosaleapplication.base.recycler;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by raoqian on 2018/9/21.
 */

public class BaseViewHolder<DATA> extends ViewHolder {
    public ActionPasser mActionPasser;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    private SparseArray<View> itemViews = new SparseArray<>();

    public int inflateLayoutId() {//不参与诗句逻辑，只作为调试方法，方便维护
        return 0;
    }

    public void fillData(DATA data) {
    }

    public void fillData2(Object data) {
    }

    public <T extends View> T getView(int id) {
        if (itemViews.get(id) != null) {
            return (T) itemViews.get(id);
        } else {
            itemViews.put(id, itemView.findViewById(id));
            return itemView.findViewById(id);
        }
    }

    protected void setTextToView(String text, int textViewId) {
        if (getView(textViewId) != null) {
            ((TextView) getView(textViewId)).setText(text);
        } else if (itemView.findViewById(textViewId) instanceof TextView) {
            itemViews.append(textViewId, itemView.findViewById(textViewId));
            ((TextView) itemView.findViewById(textViewId)).setText(text);
        } else {
            Log.e("BaseViewHolder", "setTextToView(" + text + "," + textViewId + "): textViewId error -> " + textViewId + " is not belongs TextView");
        }
    }

    protected String getTextFromView(int textViewId) {
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


    /**
     * #############################################################
     * RecyclerView 刷新不会保存Editext原有内容,需要在此确认需要保存,
     * #############################################################
     */

    protected void setKeeper(OnContentKeeper onContentKeeper, int... ids) {
        this.saveContentIds = ids;
        this.mOnContentKeeper = onContentKeeper;
    }

    public boolean needSave() {
        return mOnContentKeeper != null && saveContentIds != null && saveContentIds.length > 0;
    }

    public void setCash() {//存储数据
        for (int saveContentId : saveContentIds) {
            if (mOnContentKeeper == null) {
                contentCash.put(getPosition() + "" + saveContentId, getTextFromView(saveContentId));
            } else {
                contentCash.put(getPosition() + "" + saveContentId, mOnContentKeeper.onSave(this, saveContentId));
            }
        }
    }

    public void useCash() {//使用存储数据填充视图
        for (int saveContentId : saveContentIds) {
            if (mOnContentKeeper == null) {
                setTextToView((String) contentCash.get(getPosition() + "" + saveContentId), saveContentId);
            } else {
                Object value = contentCash.get(getPosition() + "" + saveContentId);
                if (value != null) mOnContentKeeper.onRelease(this, value, saveContentId);
            }
        }
    }

    private OnContentKeeper mOnContentKeeper;
    private Map<String, Object> contentCash = new HashMap<>();
    private int[] saveContentIds;

    /**
     * #############################################################
     * RecyclerView 刷新不会保存Editext原有内容,需要在此确认需要保存,
     *#############################################################
     */
}

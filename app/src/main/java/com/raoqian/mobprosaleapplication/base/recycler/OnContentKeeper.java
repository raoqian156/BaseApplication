package com.raoqian.mobprosaleapplication.base.recycler;

/**
 * Created by rq on 2018/9/28.
 */

public interface OnContentKeeper<VH> {

    Object onSave(VH holder, int saveContentId);

    void onRelease(VH holder, Object o, int saveContentId);
}

package com.raoqian.mobprosaleapplication.bean;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class BaseAdapter<VH extends BaseHolder, D> extends RecyclerView.Adapter<VH> {

    protected Activity mContext;
    protected AdapterHelper mHelper;
    ArrayList<Integer> HeadTag = new ArrayList<>();
    ArrayList<Integer> FootTag = new ArrayList<>();

    public BaseAdapter(Activity context) {
        this(context, null);
    }

    public BaseAdapter(Activity context, AdapterHelper helper) {
        mContext = context;
        mHelper = helper;
        RecyclerHelper.init(this);
    }

    public interface OnViewHolderAttachListener<VH extends BaseHolder> {
        void onViewDetachedFromWindow(@NonNull VH holder);

        void onViewAttachedToWindow(@NonNull VH holder);
    }

    private OnViewHolderAttachListener mOnViewHolderAttachListener;

    public void setOnViewHolderAttachListener(OnViewHolderAttachListener listener) {
        mOnViewHolderAttachListener = listener;
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull VH holder) {
        if (mOnViewHolderAttachListener != null) {
            mOnViewHolderAttachListener.onViewDetachedFromWindow(holder);
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull VH holder) {
        if (mOnViewHolderAttachListener != null) {
            mOnViewHolderAttachListener.onViewAttachedToWindow(holder);
        }
    }

    public synchronized void setHeadTag(Integer... headTag) {
        if (headTag != null && headTag.length > 0) {
            this.HeadTag.addAll(Arrays.asList(headTag));
        }
    }

    public synchronized void addFootTag(Integer... footTag) {
        if (footTag != null && footTag.length > 0) {
            this.FootTag.addAll(Arrays.asList(footTag));
        }
    }

    @Override
    public final int getItemViewType(int position) {
        if (HeadTag.size() > 0 && position < HeadTag.size()) {
            return HeadTag.get(position);
        }
        if (FootTag.size() > 0 && position >= HeadTag.size() + getData().size()) {
            return FootTag.get(position - HeadTag.size() - getData().size());
        }
        return changeViewType(position - HeadTag.size());
    }

    protected int changeViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewVHype) {
        return onCreateHolder(parent, viewVHype);
    }

    public abstract VH onCreateHolder(ViewGroup parent, int viewType);

    protected final int NOT_REAL_DATA = -1;

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (position < HeadTag.size() || position > (HeadTag.size() + getData().size())) {
            onBindingHolder(holder, NOT_REAL_DATA);
            return;
        }
        onBindingHolder(holder, position - HeadTag.size());
    }

    public abstract void onBindingHolder(VH holder, int position);

    public List<D> getData() {
        return this.mData;
    }

    private List<D> mData = new ArrayList<>();

    public void setData(List<D> data) {
        if (data == null || data.size() == 0) {
            this.mData.clear();
        }
        reSetData(data);
    }

    protected Comparator<D> onComparator() {
        return null;
    }

    public void reSetData(List<D> data) {
        this.mData = new ArrayList<>();
        this.mData.addAll(data);
        if (onComparator() != null) {
            Collections.sort(data, onComparator());
        }
        notifyDataSetChanged();
    }

    public D getDataItem(int position) {
        if (position < 0 || position >= getData().size()) {
            return null;
        }
        if (position < getData().size()) {
            return getData().get(position);
        }
        return null;
    }

    public void removeDataItem(int position) {
        if (position < getItemCount()) {
            mData.remove(position);
            notifyDataSetChanged();
        }
    }

    public void removeDataItem(D item) {
        if (item != null) {
            mData.remove(item);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return (getData() == null ? 0 : getData().size()) + HeadTag.size() + FootTag.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public AdapterHelper getHelper() {
        return mHelper;
    }

    public void setHelper(AdapterHelper mHelper) {
        this.mHelper = mHelper;
    }

    protected Activity getContext() {
        return mContext;
    }

    protected LayoutInflater getInflater() {
        return getContext().getLayoutInflater();
    }

    protected View getView(int layoutRes, ViewGroup parent) {
        return getView(layoutRes, parent, false);
    }

    protected View getView(int layoutRes, ViewGroup parent, boolean attachToRoot) {
        return getContext().getLayoutInflater().inflate(layoutRes, parent, attachToRoot);
    }

    protected String getString(int res) {
        return mContext.getString(res);
    }

    public interface AdapterHelper {
        /**
         * 适配器助手
         *
         * @param action 动作区别
         * @param data   参数，一般为String 或 position,由具体实现自行转换
         */
        void onResult(int action, Object data);
    }
}

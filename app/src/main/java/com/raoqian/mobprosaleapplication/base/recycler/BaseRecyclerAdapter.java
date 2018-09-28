package com.raoqian.mobprosaleapplication.base.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raoqian on 2018/9/21.
 */

public class BaseRecyclerAdapter<DATA, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {
    Class<?> mHolder;
    Context mContext;
    private int itemId;
    private boolean isMutiTypeAdapter = false;

    /**
     * 单布局类型  只要一种子View setData 数据条数大于 0  才会显示
     */
    public BaseRecyclerAdapter(Context context, int itemLayoutId, Class<? extends BaseViewHolder> baseViewHolderClass) {
        if (context == null || baseViewHolderClass == null || itemLayoutId == 0) {
            throw new AdapterUseException("BaseRecyclerAdapter.使用三参数构造函数 值不能为空");
        }
        this.mContext = context;
        this.mHolder = baseViewHolderClass;
        this.itemId = itemLayoutId;
    }

    public BaseRecyclerAdapter(Context context) {
        this.mContext = context;
        this.isMutiTypeAdapter = true;
    }

    private List<DATA> showData = new ArrayList<>();

    private SparseArray<Class<? extends BaseViewHolder>> headType = new SparseArray<>();
    private SparseArray<Object> headViewTags = new SparseArray<>();
    private SparseIntArray headViewResId = new SparseIntArray();

    private SparseArray<Class<? extends BaseViewHolder>> footType = new SparseArray<>();
    private SparseArray<Object> footViewTags = new SparseArray<>();
    private SparseIntArray footViewResId = new SparseIntArray();

    /**
     * 直接刷新视图，数据为空将置空列表
     *
     * @param dataList 填充数据
     */
    public void setData(List dataList) {
        showData.clear();
        if (dataList != null) {
            showData.addAll(dataList);
        }
        notifyDataSetChanged();
    }

    public DATA getDataItem(int position) {
        if (position >= 0 && position < showData.size()) {
            return showData.get(position);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < headViewTags.size() || position >= headViewTags.size() + showData.size()) {
            return position * -1;
        }
        return super.getItemViewType(position);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType < 0) {
            int realPosition = viewType * -1;
            int tagPosition;
            if (realPosition < headType.size()) {
                tagPosition = realPosition;
                return getViewHolderByClass(headType.get(tagPosition), headViewResId.get(tagPosition));
            } else {
                tagPosition = realPosition - headViewTags.size() - showData.size();
                return getViewHolderByClass(footType.get(tagPosition), footViewResId.get(tagPosition));
            }
        }
        if (isMutiTypeAdapter) {
            if (viewType < headType.size()) {
                return getViewHolderByClass(headType.get(viewType), headViewResId.get(viewType));
            } else {
                return getViewHolderByClass(footType.get(viewType - headType.size()), footViewResId.get(viewType - headType.size()));
            }
        }
        //类反射
        return getViewHolderByClass(mHolder, 0);
    }

    private VH getViewHolderByClass(Class<?> holderRoot, int resId) {
        if (holderRoot == null)
            return null;
        String error = null;
        try {
            Constructor<?>[] ctors = holderRoot.getDeclaredConstructors();
            if (ctors != null && ctors.length > 0) {
                if (resId == 0) {
                    resId = this.itemId;
                }
                VH holder = (VH) ctors[0].newInstance(new Object[]{LinearLayout.inflate(mContext, resId, null)});
                if (holder == null) {
                    throw new AdapterUseException(holderRoot.getSimpleName() + " 获取到了一个空  Holder -_-||");
                }
                if (holder.inflateLayoutId() * resId == 0) {
                    throw new AdapterUseException(holderRoot.getSimpleName() + " 布局使用错误");
                }
                if (mActionPasser != null) {
                    holder.setPasser(mActionPasser);
                }
                return holder;
            }
        } catch (InstantiationException e) {
            error = e.getMessage();
        } catch (IllegalAccessException e) {
            error = e.getMessage();
        } catch (InvocationTargetException e) {
            error = e.getMessage();
        } catch (Exception e) {
            error = e.getMessage();
        }
        throw new AdapterUseException(holderRoot.getSimpleName() + " 初始化异常:" + error);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (position < headViewTags.size()) {
            holder.fillData2(headViewTags.get(position));
        } else if (position >= headViewTags.size() + showData.size()) {
            holder.fillData2(footViewTags.get(position - headViewTags.size() - showData.size()));
        } else if (holder != null) {
            holder.fillData(getDataItem(position - headViewTags.size()));
        }
    }

    @Override
    public int getItemCount() {
        if (isMutiTypeAdapter) {
            return headType.size() + footType.size();
        }
        if (showData.size() == 0) return 0;
        return showData.size() + headViewTags.size() + footViewTags.size();
    }

    public void addFootHolder(String object, Class<? extends BaseViewHolder> footHolder, int resId) {
        int oldSize = footType.size();
        footType.put(footType.size(), footHolder);
        footViewTags.put(oldSize, object);
        footViewResId.put(oldSize, resId);
        notifyDataSetChanged();
    }

    public void addHeadHolder(String object, Class<? extends BaseViewHolder> headHolder, int resId) {
        int oldSize = headType.size();
        headType.put(headType.size(), headHolder);
        headViewTags.put(oldSize, object);
        headViewResId.put(oldSize, resId);
        notifyDataSetChanged();
    }

    private ActionPasser mActionPasser;

    public void setPasser(ActionPasser passer) {
        this.mActionPasser = passer;
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull VH holder) {
        if (holder.needSave()) {
            holder.setCash();
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull VH holder) {
        if (holder.needSave()) {
            holder.useCash();
        }
    }
}

package com.raoqian.mobprosaleapplication.base.recycler;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by raoqian on 2018/9/21
 * @
 */

public class CommentRecyclerAdapter<DATA, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {
    Context mContext;
    private int itemId;
    Class<?> mHolder;
    private boolean isAdditionalAdapter = false;//追加型布局，一般常用于每种类型布局不需要重复
    SparseArray<Class<? extends BaseViewHolder>> multipleHolder;

    /**
     * 单布局类型  只要一种子View setData 数据条数大于 0  才会显示
     *
     * @param itemLayoutId 对 ViewHolder 的描述，必须与 BaseViewHolder 使用保持一致
     */
    public CommentRecyclerAdapter(Context context, @LayoutRes int itemLayoutId, Class<? extends BaseViewHolder> baseViewHolderClass) {
        if (context == null || baseViewHolderClass == null || itemLayoutId == 0) {
            throw new AdapterUseException("CommentRecyclerAdapter.使用三参数构造函数 值不能为空");
        }
        this.mContext = context;
        this.mHolder = baseViewHolderClass;
        this.itemId = itemLayoutId;
    }

    /**
     * 多类型布局   子视图有多种类型
     *
     * @param maps key  对 ViewHolder 的描述，value key布局对应的 BaseViewHolder.class，必须与 BaseViewHolder 使用保持一致
     */
    public CommentRecyclerAdapter(Context context, @NonNull SparseArray<Class<? extends BaseViewHolder>> maps) {
        this.mContext = context;
        this.multipleHolder = maps;
        getMultipleHolderType(null, 0);//进行检测
    }

    /**
     * 追加型布局
     */
    public CommentRecyclerAdapter(Context context) {
        this.mContext = context;
        this.isAdditionalAdapter = true;
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
        if (multipleHolder != null) {
            return getMultipleHolderType(getDataItem(position - headType.size()), position - headType.size());
        }
        return super.getItemViewType(position);
    }

    protected int getMultipleHolderType(DATA dataItem, int position) {
        throw new AdapterUseException(" 多类型布局使用错误，必须复写 getMultipleHolderType() 方法,并且不调用父类方法  ");
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
        if (isAdditionalAdapter) {
            if (viewType < headType.size()) {
                return getViewHolderByClass(headType.get(viewType), headViewResId.get(viewType));
            } else {
                return getViewHolderByClass(footType.get(viewType - headType.size()), footViewResId.get(viewType - headType.size()));
            }
        }
        if (multipleHolder != null) {
            Class clazz = multipleHolder.get(viewType);
            if (clazz == null) {
                throw new AdapterUseException(" 多类型布局使用错误，multipleHolder key 为 R.layout.id   value 为 Holder.class  ");
            }
            return getViewHolderByClass(clazz, viewType);
        }
        return getViewHolderByClass(mHolder, this.itemId);
    }

    /**
     * 通过反射获取Holder实例
     */
    private VH getViewHolderByClass(@NonNull Class<?> holderRoot, @LayoutRes int resId) {
        String error = null;
        try {
            Constructor<?>[] ctors = holderRoot.getDeclaredConstructors();
            if (ctors != null && ctors.length > 0) {
                VH holder = (VH) ctors[0].newInstance(new Object[]{LinearLayout.inflate(mContext, resId, null)});
                if (holder == null) {
                    throw new AdapterUseException(holderRoot.getSimpleName() + " 获取到了一个空  Holder -_-||");
                }
                if (holder.inflateLayoutId() != resId) {
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
        holder.itemView.setTag(position);
        if (holder instanceof OnContentKeeper) {
            Log.e("onBindViewHolder", "addView");
            Log.e("CommentRecyclerAdapter", "onBindViewHolder: " + holder.getPosition());
            useCash(holder);
            return;
        }
        if (position < headViewTags.size()) {
            holder.fillObject(headViewTags.get(position));
        } else if (position >= headViewTags.size() + showData.size()) {
            holder.fillObject(footViewTags.get(position - headViewTags.size() - showData.size()));
        } else {
            holder.fillData(getDataItem(position - headViewTags.size()));
        }
    }

    @Override
    public int getItemCount() {
        if (isAdditionalAdapter) {
            return headType.size() + footType.size();
        }
        if (showData.size() == 0) return 0;
        return showData.size() + headViewTags.size() + footViewTags.size();
    }

    /**
     * 由于 SparseArray 的特性，addHeadHolder addFootHolder 只能从前面往后面追加，要增加指定顺序需更换为HasMap
     */
    public void addFootHolder(String object, Class<? extends BaseViewHolder> footHolder, @LayoutRes int resId) {
        int oldSize = footType.size();
        footType.put(footType.size(), footHolder);
        footViewTags.put(oldSize, object);
        footViewResId.put(oldSize, resId);
    }

    public void addHeadHolder(String object, Class<? extends BaseViewHolder> headHolder, @LayoutRes int resId) {
        int oldSize = headType.size();
        headType.put(headType.size(), headHolder);
        headViewTags.put(oldSize, object);
        headViewResId.put(oldSize, resId);
    }

    public void clearHeadView() {
        headType.clear();
        headViewTags.clear();
        headViewResId.clear();
        notifyDataSetChanged();
    }

    public void clearFootView() {
        footType.clear();
        footViewTags.clear();
        footViewResId.clear();
    }

    private ActionPasser mActionPasser;

    public void setPasser(ActionPasser passer) {
        this.mActionPasser = passer;
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull VH holder) {
        if (holder instanceof OnContentKeeper) {
            setCash(holder);
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull VH holder) {
        if (holder instanceof OnContentKeeper) {
            useCash(holder);
        }
        if (holder.getPosition() == getItemCount() - 1) {
            onAttachedToBottom(holder.getPosition());
        } else if (holder.getPosition() == 0) {
            onAttachedToTop();
        }
    }

    public interface OnAttachedToBottomListener {
        void onAttachedToBottom(int position);
    }

    private OnAttachedToBottomListener mOnAttachedToBottomListener;

    public void setOnAttachedToBottomListener(OnAttachedToBottomListener l) {
        this.mOnAttachedToBottomListener = l;
    }

    protected void onAttachedToBottom(int position) {
        if (mOnAttachedToBottomListener != null) {
            mOnAttachedToBottomListener.onAttachedToBottom(position);
        }
        Log.e("CommentRecyclerAdapter", "onAttachedToBottom: ");
    }

    private void onAttachedToTop() {
        Log.e("CommentRecyclerAdapter", "onAttachedToTop: ");
    }

    /**
     * ##########################################################################
     * RecyclerView 快速滑动时 EditText 等视图内容会随着视图复用而造成数据多余
     * Holder.class 实现 OnContentKeeper 即可解决此问题
     * ##########################################################################
     */

    public void setCash(VH holder) {//存储数据
        if (((OnContentKeeper) holder).getSaveViewId() == null || ((OnContentKeeper) holder).getSaveViewId().length == 0) {
            return;
        }
        for (int saveContentId : ((OnContentKeeper) holder).getSaveViewId()) {
            Object save = ((OnContentKeeper) holder).getSave(saveContentId);
            if (save != null) {
                contentCash.put(holder.getPosition() + "" + saveContentId, save);
                Log.e("CommentRecyclerAdapter", holder.getPosition() + " setCash: " + save);
            }
        }
    }

    public void useCash(VH holder) {//使用存储数据填充视图
        if (((OnContentKeeper) holder).getSaveViewId() == null || ((OnContentKeeper) holder).getSaveViewId().length == 0) {
            return;
        }
        for (int saveContentId : ((OnContentKeeper) holder).getSaveViewId()) {
            Object value = contentCash.get(holder.getPosition() + "" + saveContentId);
            ((OnContentKeeper) holder).onRelease(value, saveContentId);
        }
    }

    private Map<String, Object> contentCash = new HashMap<>();

}

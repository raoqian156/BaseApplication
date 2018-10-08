package com.raoqian.mobprosaleapplication.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import static com.raoqian.mobprosaleapplication.base.BaseActivity.getMain;

/**
 * Created by raoqian on 2017/3/24.
 */

public abstract class BaseFragment extends Fragment implements FragmentUserVisibleController.UserVisibleCallback {

    protected static String DATA = "Fragment.pass.data";
    FragmentUserVisibleController userVisibleController;

    public BaseFragment() {
        userVisibleController = new FragmentUserVisibleController(this, this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("BaseFragment", "--------" + this.getClass().getName() + "----------");
    }

    View viewStatus;

    protected View getViewStatus() {
        return viewStatus;
    }

    /**
     * @param parent 当此为LinearLayout时id只需要传最上面View 的ID
     * @param ids    相对状态栏需要下移的原ViewID
     */
    protected void setNeedMoveToBottomViewId(View parent, int... ids) {
//        if (ids == null || ids.length == 0) {
//            return;
//        }
//        viewStatus = new View(getContext());
//        viewStatus.setBackgroundColor(Color.argb(255, MAIN_COLOR_RED, MAIN_COLOR_GREEN, MAIN_COLOR_BLUE));
//        if (parent instanceof LinearLayout) {
//            LinearLayout.LayoutParams statusParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, BASE_STATUS_BAR_HEIGHT);
//            ((LinearLayout) parent.findViewById(ids[0]).getParent()).addView(viewStatus, 0, statusParam);
//        } else if (parent instanceof FrameLayout) {
//            FrameLayout.LayoutParams statusParam = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, BASE_STATUS_BAR_HEIGHT);
//            ((FrameLayout) parent).addView(viewStatus, statusParam);
//            for (int id : ids) {
//                FrameLayout.LayoutParams flp = (FrameLayout.LayoutParams) parent.findViewById(id).getLayoutParams();
//                int oldMT = flp.topMargin;
//                flp.setMargins(0, BASE_STATUS_BAR_HEIGHT + oldMT, 0, 0);
//            }
//
//        } else if (parent instanceof RelativeLayout) {
//            RelativeLayout.LayoutParams statusParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, BASE_STATUS_BAR_HEIGHT);
//            ((RelativeLayout) parent).addView(viewStatus, statusParam);
//            for (int id : ids) {
//                RelativeLayout.LayoutParams flp = (RelativeLayout.LayoutParams) parent.findViewById(id).getLayoutParams();
//                int[] ruls = flp.getRules();
//                if (ruls[RelativeLayout.BELOW] > 0 || ruls[RelativeLayout.ALIGN_PARENT_BOTTOM] > 0) {
//                } else {
//                    int oldMT = flp.topMargin;
//                    flp.setMargins(0, BASE_STATUS_BAR_HEIGHT + oldMT, 0, 0);
//                }
//            }
//        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(onGetLayoutId(), container, false);
        initView(view);
        return view;
    }

    protected void runOnUiThread(Runnable runnable) {
        getMain().post(runnable);
    }

    protected void runOnUiThread(Runnable runnable, long delayedTime) {
        getMain().postDelayed(runnable, delayedTime);
    }

    protected abstract void initView(View view);

    protected abstract int onGetLayoutId();

    public static final String ACTION_LOGIN_OUT = "loginOut";

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        if (view.findViewById(R.id.common_title_right_text) != null)
//            view.findViewById(R.id.common_title_right_text).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    EventBus.getDefault().post(new EventUtils.ObjectEvent(null, ACTION_LOGIN_OUT));
//                }
//            });
//    }

    Map<String, Integer> selectedRecord = new HashMap<>();
    ProgressDialog dialogProgress;

    protected void showLoading(String content) {
        dialogProgress = new ProgressDialog(getContext());
        dialogProgress.setMessage(content);
        dialogProgress.show();
    }

    protected void showLoading(int res) {
        showLoading(getContext().getResources().getString(res));
    }

    protected void hideProgress() {
        if (dialogProgress != null && dialogProgress.isShowing()) {
            dialogProgress.dismiss();
        }
    }
//
//    protected void needBack(View view) {
//        if (view != null) {
//            View backCLickView = view.findViewById(R.id.img_back);
//            if (backCLickView != null && backCLickView.isShown()) {
//                backCLickView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        getActivity().finish();
//                    }
//                });
//            } else if (backCLickView != null && !backCLickView.isShown()) {
//                backCLickView.setVisibility(View.VISIBLE);
//                backCLickView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        getActivity().finish();
//                    }
//                });
//            }
//        }
//    }
//
//    protected void hideBack(View view) {
//        if (view != null) {
//            if (view.findViewById(R.id.img_back) != null) {
//                view.findViewById(R.id.img_back).setVisibility(View.GONE);
//            }
//        }
//    }
//
//    public int getColor(int colorResId) {
//        return getResources().getColor(colorResId);
//    }
//
//    protected void initRedBar(View view, String redTitle1, String redTitle2, final ViewPager viewPager) {
//        final TextView topBar1 = (TextView) view.findViewById(R.id.red_top_bar);
//        final TextView topBar2 = (TextView) view.findViewById(R.id.red_top_bar2);
//        final String key = this.getClass().getName();
//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = 0;
//                if (v == topBar2) {
//                    position = 1;
//                }
//                viewPager.setCurrentItem(position);
//                topBar1.setSelected(position == 0);
//                topBar2.setSelected(position == 1);
//                selectedRecord.put(key, position);
//
//            }
//        };
//        topBar1.setText(redTitle1);
//        topBar2.setText(redTitle2);
//        topBar1.setOnClickListener(listener);
//        topBar2.setOnClickListener(listener);
//        if (selectedRecord.containsKey(key)) {
//            topBar1.setSelected(0 == selectedRecord.get(key));
//            topBar2.setSelected(1 == selectedRecord.get(key));
//        } else {
//            topBar1.setSelected(true);
//            selectedRecord.put(key, 0);
//        }
//    }
//
//    protected TextView[] tab;
//    private OnFragmentTopBarOnClickListener mListener;
//    boolean hasVLine = false;
//
//    /**
//     * 修改对应顶部选项卡显示内容
//     *
//     * @param position
//     * @param content
//     */
//    protected void changeTopInclude(int position, String content) {
//        if (tab != null && position > -1 && position < tab.length) {
//            tab[position].setText(content);
//        }
//    }
//
//    protected void initFragmentInclude(View view, String... tabText) {
//        if (tabText == null || tabText.length == 0) {
//            view.findViewById(R.id.top_part).setVisibility(View.GONE);
//            return;
//        }
//        hasVLine = view.findViewById(R.id.top_bar_v_line2) != null;
//        tab = new TextView[4];
//        tab[0] = (TextView) view.findViewById(R.id.top_bar1);
//        tab[1] = (TextView) view.findViewById(R.id.top_bar2);
//        tab[2] = (TextView) view.findViewById(R.id.top_bar3);
//        tab[3] = (TextView) view.findViewById(R.id.top_bar4);
//        if (tabText.length == 4) {
//            if (TextUtils.isEmpty(tabText[0])) {
//                if (tab[0].getTag() == null) {
//                    TLog.error("BaseFragment", "0 no tag");
//                    ((LinearLayout) tab[0].getParent()).setVisibility(View.GONE);
//                } else {
//                    TLog.error("BaseFragment", "0 has tag " + tab[0].getTag());
//                    tab[0].setVisibility(View.GONE);
//                }
//            } else {
//                tab[0].setText(tabText[0]);
//            }
//            if (TextUtils.isEmpty(tabText[1])) {
//                if (tab[1].getTag() == null) {
//                    TLog.error("BaseFragment", "1 no tag");
//                    ((LinearLayout) tab[1].getParent()).setVisibility(View.GONE);
//                } else {
//                    TLog.error("BaseFragment", "1 has tag");
//                    tab[1].setVisibility(View.GONE);
//                }
//                if (hasVLine) {
//                    view.findViewById(R.id.top_bar_v_line2).setVisibility(View.GONE);
//                }
//            } else {
//                tab[1].setText(tabText[1]);
//            }
//            if (TextUtils.isEmpty(tabText[2])) {
//                if (tab[2].getTag() == null) {
//                    ((LinearLayout) tab[2].getParent()).setVisibility(View.GONE);
//                } else {
//                    tab[2].setVisibility(View.GONE);
//                }
//                if (hasVLine) {
//                    view.findViewById(R.id.top_bar_v_line3).setVisibility(View.GONE);
//                }
//            } else {
//                tab[2].setText(tabText[2]);
//            }
//            if (TextUtils.isEmpty(tabText[3])) {
//
//                if (tab[3].getTag() == null) {
//                    ((LinearLayout) tab[3].getParent()).setVisibility(View.GONE);
//                } else {
//                    tab[3].setVisibility(View.GONE);
//                }
//                if (hasVLine) {
//                    view.findViewById(R.id.top_bar_v_line4).setVisibility(View.GONE);
//                }
//            } else {
//                tab[3].setText(tabText[3]);
//            }
//        }
//        tab[0].setSelected(true);
////        tab[0].setTag(0);  --  排序用到
//    }
//
//    protected void initFragmentNoArrowInclude(View view, String... tabText) {
//        if (tabText == null || tabText.length == 0) {
//            view.findViewById(R.id.top_part).setVisibility(View.GONE);
//            return;
//        }
//        hasVLine = view.findViewById(R.id.top_bar_v_line2) != null;
//        tab = new TextView[4];
//        tab[0] = (TextView) view.findViewById(R.id.top_bar1);
//        tab[1] = (TextView) view.findViewById(R.id.top_bar2);
//        tab[2] = (TextView) view.findViewById(R.id.top_bar3);
//        tab[3] = (TextView) view.findViewById(R.id.top_bar4);
//        if (tabText != null && tabText.length == 4) {
//            tab[0].setText(tabText[0]);
//            if (!TextUtils.isEmpty(tabText[0])) {
//                tab[0].setText(tabText[1]);
//            }
//            if (TextUtils.isEmpty(tabText[1])) {
//                if (hasVLine) {
//                    view.findViewById(R.id.top_bar_v_line2).setVisibility(View.GONE);
//                }
//            } else {
//                tab[1].setText(tabText[1]);
//            }
//            if (TextUtils.isEmpty(tabText[2])) {
//                if (hasVLine) {
//                    view.findViewById(R.id.top_bar_v_line3).setVisibility(View.GONE);
//                }
//            } else {
//                tab[2].setText(tabText[2]);
//            }
//            if (TextUtils.isEmpty(tabText[3])) {
//                tab[3].setVisibility(View.GONE);
//                if (hasVLine) {
//                    view.findViewById(R.id.top_bar_v_line4).setVisibility(View.GONE);
//                }
//            } else {
//                tab[3].setText(tabText[3]);
//            }
//        }
//        tab[0].setSelected(true);
//    }
//
//    public void setTopBarClickListener(OnFragmentTopBarOnClickListener listener) {
//        if (listener != null) {
//            mListener = listener;
//            tab[0].setOnClickListener(clickListener);
//            tab[1].setOnClickListener(clickListener);
//            tab[2].setOnClickListener(clickListener);
//            tab[3].setOnClickListener(clickListener);
//        }
//    }
//
//    protected TextView[] centerTab;
//    private OnFragmentTopBarOnClickListener mCenterListener;
//
//    protected void initCenterInclude(View view, OnFragmentTopBarOnClickListener listener, String... title) {
//        mCenterListener = listener;
//        centerTab = new TextView[title.length];
//        centerTab[0] = (TextView) view.findViewById(R.id.red_center_bar1);
//        centerTab[1] = (TextView) view.findViewById(R.id.red_center_bar2);
//        centerTab[2] = (TextView) view.findViewById(R.id.red_center_bar3);
//        centerTab[0].setSelected(true);
//        centerTab[0].setOnClickListener(clickListener);
//        centerTab[1].setOnClickListener(clickListener);
//        centerTab[2].setOnClickListener(clickListener);
//        centerTab[0].setText(title[0]);
//        centerTab[1].setText(title[1]);
//        centerTab[2].setText(title[2]);
//    }
//
//    public void setTitle(View view, String title) {
//        if (view.findViewById(R.id.common_title_text) != null) {
//            ((TextView) view.findViewById(R.id.common_title_text)).setText(title);
//        }
//    }
//
//    public void setTitle(View view, int title) {
//        if (view.findViewById(R.id.common_title_text) != null) {
//            ((TextView) view.findViewById(R.id.common_title_text)).setText(title);
//        }
//    }
//
//    public void setRedBarTitle(View view, int title) {
//        if (view.findViewById(R.id.common_title_text) != null) {
//            ((TextView) view.findViewById(R.id.common_title_text)).setText(title);
//        }
//    }
//
//    View.OnClickListener clickListener = new View.OnClickListener() {
//
//        @Override
//        public void onClick(View v) {
//            if (mListener != null && (v == tab[0] || v == tab[1] || v == tab[2] || v == tab[3])) {
//                int position = 0;
//                if (v == tab[1]) {
//                    position = 1;
//                } else if (v == tab[2]) {
//                    position = 2;
//                } else if (v == tab[3]) {
//                    position = 3;
//                }
////                if (hasVLine) {
////                    ((LinearLayout) tab[0].getParent()).setSelected(position == 0);
////                    ((LinearLayout) tab[1].getParent()).setSelected(position == 1);
////                    ((LinearLayout) tab[2].getParent()).setSelected(position == 2);
////                    ((LinearLayout) tab[3].getParent()).setSelected(position == 3);
////                }
//                tab[0].setSelected(position == 0);
//                tab[1].setSelected(position == 1);
//                tab[2].setSelected(position == 2);
//                tab[3].setSelected(position == 3);
//                tab[position].setTag(0);
//                mListener.onClickItem(position, v);
//            }
//            if (mCenterListener != null && (v == centerTab[0] || v == centerTab[2] || v == centerTab[1])) {
//                int position = 0;
//                if (v == centerTab[1]) {
//                    position = 1;
//                } else if (v == centerTab[2]) {
//                    position = 2;
//                }
//                centerTab[0].setSelected(position == 0);
//                centerTab[1].setSelected(position == 1);
//                centerTab[2].setSelected(position == 2);
//                mCenterListener.onClickItem(position, v);
//            }
//
//        }
//    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userVisibleController.activityCreated();
    }

    @Override
    public void onResume() {
        super.onResume();
        userVisibleController.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        userVisibleController.pause();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        userVisibleController.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void setWaitingShowToUser(boolean waitingShowToUser) {
        userVisibleController.setWaitingShowToUser(waitingShowToUser);
    }

    @Override
    public boolean isWaitingShowToUser() {
        return userVisibleController.isWaitingShowToUser();
    }

    @Override
    public boolean isVisibleToUser() {
        return userVisibleController.isVisibleToUser();
    }

    @Override
    public void callSuperSetUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }


    @Override
    final public void onVisibleToUserChanged(boolean isVisibleToUser, boolean invokeInResumeOrPause, boolean isFirstTimeSHow) {
        if (isFirstTimeSHow) {
            onFirstTimeShow();
            return;
        }
        onHideToShow(isVisibleToUser);
    }

    protected void onHideToShow(boolean isHideToShow) {
        if (isHideToShow) {
            Log.w("BaseFragment", this.getClass().getName() + ".isHideToShow ");
//            onShowEmpty();
        } else {
            Log.w("BaseFragment", this.getClass().getName() + ".isShowToHide ");
//            onHideEmpty();
        }
    }

    private boolean hasShowOneTime = false;

    protected void onFirstTimeShow() {
        hasShowOneTime = true;
    }

    public interface OnFragmentTopBarOnClickListener {
        void onClickItem(int position, View view);
    }

//    public void dismissDialog() {
//        BaseDialogFragment dialog = (BaseDialogFragment) (getActivity()).getSupportFragmentManager().findFragmentByTag(((BaseActivity) getActivity()).TAG_DIALOG);
//        if (dialog != null) {
//            ShowSupport.SupportRunnable runnable = new ShowSupport.SupportRunnable((getActivity()).getSupportFragmentManager(), dialog);
//            runnable.setTag(((BaseActivity) getActivity()).TAG_DIALOG, true);
//            ((BaseActivity) getActivity()).mShow.execute(runnable);
//        }
//
//    }
//
//    public void displayDialog(DialogFragment dialog, Bundle bundle) {
//        this.dismissDialog();
//        dialog.setArguments(bundle);
//        ShowSupport.SupportRunnable runnable = new ShowSupport.SupportRunnable((getActivity()).getSupportFragmentManager(), dialog);
//        runnable.setTag(((BaseActivity) getActivity()).TAG_DIALOG, false);
//        ((BaseActivity) getActivity()).mShow.execute(runnable);
//    }
//
//    public void displayDialog(DialogFragment dialog) {
//        this.dismissDialog();
//        ShowSupport.SupportRunnable runnable = new ShowSupport.SupportRunnable((getActivity()).getSupportFragmentManager(), dialog);
//        runnable.setTag(((BaseActivity) getActivity()).TAG_DIALOG, false);
//        ((BaseActivity) getActivity()).mShow.execute(runnable);
//    }
//
//    protected static HasTopPopWindow[] createPop(Activity activity, final View view, final int topId, final OnCombineListener listener, final List<String>... data) {
//        int size = data.length;
//        HasTopPopWindow[] popupWindow = new HasTopPopWindow[size];
//        for (int i = 0; i < size; i++) {
//            final List<String> pop1Data = data[i];
//            final int finalI = i;
//            popupWindow[i] = new HasTopPopWindow(activity) {
//                @Override
//                protected List<String> onGetData() {
//                    return pop1Data;
//                }
//
//                @Override
//                protected View onGetTopView() {
//                    return view.findViewById(topId);
//                }
//
//                @Override
//                protected OnPopClickListener onItemClickListener() {
//                    return new OnPopClickListener() {
//                        @Override
//                        public void oItemClick(int position) {
//                            listener.onPopCombineClickListener(finalI, position, pop1Data.get(position));
//                        }
//                    };
//                }
//            };
//        }
//        return popupWindow;
//    }
//
//    protected static HasTopPopWindow createPopWithTopView(Activity activity, final View view, final OnCombineListener listener, final List<String> data) {
//        HasTopPopWindow popupWindow = new HasTopPopWindow(activity) {
//            @Override
//            protected List<String> onGetData() {
//                return data;
//            }
//
//            @Override
//            protected View onGetTopView() {
//                return view;
//            }
//
//            @Override
//            protected OnPopClickListener onItemClickListener() {
//                return new OnPopClickListener() {
//                    @Override
//                    public void oItemClick(int position) {
//                        listener.onPopCombineClickListener(0, position, data.get(position));
//                    }
//                };
//            }
//        };
//        return popupWindow;
//    }
//
//    protected interface OnCombineListener {
//        void onPopCombineClickListener(int popPosition, int itemPosition, String name);
//    }
//
//
//    View empty;
//
//    private int mLinearLayoutIndex = 1;
//
//    public int getLinearLayoutIndex() {
//        return mLinearLayoutIndex;
//    }
//
//    /**
//     * @param recycle
//     * @param linearLayoutIndex 线性布局时，需要手动定位到 @param recycle 所在的外层之前 //此应用中为倒数
//     */
//    protected void addEmptyViewOnRecycler(final RecyclerView recycle, int linearLayoutIndex) {
//        this.mLinearLayoutIndex = linearLayoutIndex;
//        addEmptyViewOnRecycler(recycle);
//    }
//
//    /**
//     * @param recycle 所绑定的视图,会自动根据视图内容判断
//     */
//    protected void addEmptyViewOnRecycler(final RecyclerView recycle) {
//        TLog.error("BaseFragment", "71");
//        if (recycle == null || recycle.getAdapter() == null) {
//            return;
//        }
////        if (canShowEmptyView(recycle)) {
////            return;
////        }
//        empty = getActivity().getLayoutInflater().inflate(R.layout.view_empty_on_recycleview, recycle, false);
//        TextView emptyText = (TextView) empty.findViewById(R.id.empty_text);
//        ImageView emptyImage = (ImageView) empty.findViewById(R.id.empty_image);
//        onStatue(emptyText, emptyImage);
//        ViewParent view = recycle.getParent();
//        if (view instanceof RelativeLayout) {
//            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            DisplayMetrics dm = getResources().getDisplayMetrics();
//            empty.setY(dm.heightPixels / dm.density);
//            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
//            ((RelativeLayout) view).addView(empty, lp);
//            TLog.error("BaseFragment", "is RelativeLayout");
//        } else if (view instanceof FrameLayout) {
//            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
//            ((FrameLayout) view).addView(empty, 1, lp);
//            TLog.error("BaseFragment", "is FrameLayout  -> 1");
//        } else if (view instanceof LinearLayout) {
//            TLog.error("BaseFragment", "is LinearLayout");
//            LinearLayout layout = (LinearLayout) view;
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
//            layout.addView(empty, layout.getChildCount() - getLinearLayoutIndex(), lp);
//        } else {
//            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            ((ViewGroup) view).addView(empty, lp);
//            TLog.error("BaseFragment", "is ViewGroup");
//        }
////        addContentView(empty, lp);
//        if (recycle.getAdapter() == null) {
//            TLog.error("BaseFragment", "recycle.getAdapter() == null.");
//            return;
//        }
//
//        recycle.getAdapter().registerAdapterDataObserver(
//                new RecyclerView.AdapterDataObserver() {
//                    @Override
//                    public void onChanged() {
////                        TLog.error("BaseFragment", "104  -> " + recycle.getAdapter().getItemCount());
//                        setEmptyShowStatue(recycle.getAdapter().getItemCount());
//                    }
//                }
//
//        );
//    }
//
//    private final String noData = "没有数据";
//    private int dataCount = -1;
//
//    protected void onStatue(final TextView emptyText, final ImageView emptyImage) {
//    }
//
//    private void setEmptyShowStatue(final int recycleNum) {
//        if (empty != null) {
//            if (recycleNum == 0) {
//                dataCount = 0;
//                TextView emptyText = (TextView) empty.findViewById(R.id.empty_text);
//                ImageView emptyImage = (ImageView) empty.findViewById(R.id.empty_image);
//                if (empty.isShown()) {
//                    emptyText.setText(noData);
////                    emptyImage.setImageResource(R.mipmap.icon_show_bianyaqi);
//                }
//                onStatue(emptyText, emptyImage);
//                onShowEmpty();
//            } else {
//                empty.setVisibility(View.GONE);
//            }
//        }
//    }
//
//    protected void onShowEmpty() {
//        if (empty != null) {
//            if (!empty.isShown()) {
//                if (dataCount == 0 && hasShowOneTime) {
//                    empty.setVisibility(View.VISIBLE);
//                }
//            }
//        }
//    }
//
//    protected void onHideEmpty() {
//        if (empty != null) {
//            if (empty.isShown()) {
//                empty.setVisibility(View.GONE);
//            }
//        }
//    }

}

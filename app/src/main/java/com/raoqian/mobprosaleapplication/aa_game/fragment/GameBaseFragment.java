package com.raoqian.mobprosaleapplication.aa_game.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.raoqian.mobprosaleapplication.R;
import com.raoqian.mobprosaleapplication.aa_game.WuQi;
import com.raoqian.mobprosaleapplication.aa_game.ZhuangBei;
import com.raoqian.mobprosaleapplication.base.BaseFragment;
import com.raoqian.mobprosaleapplication.utils.TLog;


/**
 * Created by Administrator on 2018/5/29.
 */

public class GameBaseFragment extends BaseFragment {

    ZhuangBei mZhuangBei = new ZhuangBei("随机装备");
    WuQi mWuQi = new WuQi("随机武器");

    int wuqi_shenMing;
    int wuqi_gongJi;
    int wuqi_fangYu;
    int wuqi_zhiLi;
    int wuqi_yanZhi;
    int wuqi_minJie;

    int zhuangbei_shenMing;
    int zhuangbei_gongJi;
    int zhuangbei_fangYu;
    int zhuangbei_zhiLi;
    int zhuangbei_yanZhi;
    int zhuangbei_minJie;

    View rootView;

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_game_base;
    }

    @Override
    protected void initView(View view) {
        setNeedMoveToBottomViewId(view, R.id.title);
        rootView = view;
    }

    public ZhuangBei getmZhuangBei() {
        zhuangbei_shenMing = getValue(R.id.zhuangbei_shenMing);
        zhuangbei_gongJi = getValue(R.id.zhuangbei_gongJi);
        zhuangbei_fangYu = getValue(R.id.zhuangbei_fangYu);
        zhuangbei_zhiLi = getValue(R.id.zhuangbei_zhiLi);
        zhuangbei_yanZhi = getValue(R.id.zhuangbei_yanZhi);
        zhuangbei_minJie = getValue(R.id.zhuangbei_minJie);
        mZhuangBei.setShenMing(zhuangbei_shenMing);
        mZhuangBei.setGongJi(zhuangbei_gongJi);
        mZhuangBei.setFangYu(zhuangbei_fangYu);
        mZhuangBei.setZhiLi(zhuangbei_zhiLi);
        mZhuangBei.setYanZhi(zhuangbei_yanZhi);
        mZhuangBei.setMinJie(zhuangbei_minJie);
        TLog.bean("GameBaseFragment", mZhuangBei);
        return mZhuangBei;
    }

    public WuQi getmWuQi() {
        wuqi_shenMing = getValue(R.id.wuqi_shenMing);
        wuqi_gongJi = getValue(R.id.wuqi_gongJi);
        wuqi_fangYu = getValue(R.id.wuqi_fangYu);
        wuqi_zhiLi = getValue(R.id.wuqi_zhiLi);
        wuqi_yanZhi = getValue(R.id.wuqi_yanZhi);
        wuqi_minJie = getValue(R.id.wuqi_minJie);
        mWuQi.setShenMing(wuqi_shenMing);
        mWuQi.setGongJi(wuqi_gongJi);
        mWuQi.setFangYu(wuqi_fangYu);
        mWuQi.setZhiLi(wuqi_zhiLi);
        mWuQi.setYanZhi(wuqi_yanZhi);
        mWuQi.setMinJie(wuqi_minJie);
        TLog.bean("GameBaseFragment", mWuQi);
        return mWuQi;
    }

    private int getValue(int id) {
        EditText edt = rootView.findViewById(id);
        if (!TextUtils.isEmpty(edt.getText())) {
            return Integer.parseInt(edt.getText().toString());
        }
        return 0;
    }
}

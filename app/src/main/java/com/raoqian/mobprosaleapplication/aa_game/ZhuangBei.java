package com.raoqian.mobprosaleapplication.aa_game;

import android.util.Log;

/**
 * Created by raoqian on 2018/9/30.
 */

public class ZhuangBei implements HumanParam {
    public static int SHENMING_MAX = 100;
    public static int GONGJI_MAX = 10;
    public static int FANGYU_MAX = 10;
    public static int ZHILI_MAX = 5;
    public static int YANZHI_MAX = 5;
    public static int MINJIE_MAX = 5;
    String name = "";
    int shenMing = 0;
    int gongJi = 0;
    int fangYu = 0;
    int zhiLi = 0;
    int yanZhi = 0;
    int minJie = 0;

    public ZhuangBei(String name) {
        this.name = name;
        this.shenMing = (int) (SHENMING_MAX * Math.random());
        this.gongJi = (int) (GONGJI_MAX * Math.random());
        this.fangYu = (int) (FANGYU_MAX * Math.random());
        this.zhiLi = (int) (ZHILI_MAX * Math.random());
        this.yanZhi = (int) (YANZHI_MAX * Math.random());
        this.minJie = (int) (MINJIE_MAX * Math.random());
    }

    public String getName() {
        return name;
    }

    public void setShenMing(int shenMing) {
        this.shenMing = shenMing;
    }

    public void setGongJi(int gongJi) {
        this.gongJi = gongJi;
    }

    public void setFangYu(int fangYu) {
        this.fangYu = fangYu;
    }

    public void setZhiLi(int zhiLi) {
        this.zhiLi = zhiLi;
    }

    public void setYanZhi(int yanZhi) {
        this.yanZhi = yanZhi;
    }

    public void setMinJie(int minJie) {
        this.minJie = minJie;
    }

    @Override
    public int getTZ() {
        return 0;
    }

    @Override
    public int getSM() {
        return shenMing;
    }

    @Override
    public int getGJ() {
        return gongJi;
    }

    @Override
    public int getFY() {
        return fangYu;
    }

    @Override
    public int getZL() {
        return zhiLi;
    }

    @Override
    public int getYZ() {
        return yanZhi;
    }

    @Override
    public int getMJ() {
        return minJie;
    }

    @Override
    public String toString() {
        return "ZhuangBei{" +
                "name='" + name + '\'' +
                ", SM=" + shenMing +
                ", GJ=" + gongJi +
                ", FY=" + fangYu +
                ", ZL=" + zhiLi +
                ", YZ=" + yanZhi +
                ", MJ=" + minJie +
                '}';
    }

    public void create(ZhuangBei zhuangBei) {
        if (zhuangBei == null) return;
        this.name = zhuangBei.getName();
        this.shenMing = (int) (zhuangBei.getSM() * Math.random());
        this.gongJi = (int) (zhuangBei.getGJ() * Math.random());
        this.fangYu = (int) (zhuangBei.getFY() * Math.random());
        this.zhiLi = (int) (zhuangBei.getZL() * Math.random());
        this.yanZhi = (int) (zhuangBei.getYZ() * Math.random());
        this.minJie = (int) (zhuangBei.getMJ() * Math.random());
        Log.e("WuQi", "重新生成装备：" + toString());
    }
}

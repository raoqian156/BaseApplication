package com.raoqian.mobprosaleapplication.aa_game;


import android.util.Log;

/**
 * Created by raoqian on 2018/9/30.
 */

public class WuQiOld implements HumanParam {

    public static int SM_MAX = 10;
    public static int GJ_MAX = 10;
    public static int FY_MAX = 0;
    public static int ZL_MAX = 5;
    public static int YZ_MAX = 5;
    public static int MJ_MAX = 5;


    public enum TYPE {
        JIAN       //剑 - 【普通攻击】有40%的几率引发{流血}
        , TOUZ     //投掷物 -【普通攻击】将不会被引发【反击】【招架】  
        , DUNQ     //钝器 - 【普通攻击】有25%几率引发{晕眩}
        , JIXI     //枪械 -  【普通攻击】不会引发【反击】【招架】有40%几率{洞穿}
        , FAZH     //法杖 -  增加所有技能发动机率5%
    }

    String name = "";
    int sm = 0;
    int gj = 0;
    int fy = 0;
    int zl = 0;
    int yz = 0;
    int mj = 0;
    private double skillAdd = 0;
    private TYPE mTYPE = null;

    public WuQiOld(String name) {
        this.name = name;
    }

    public WuQiOld(String name, TYPE type, int sm, int gj, int fy, int zl, int yz, int mj) {
        this.name = name;
        this.mTYPE = type;
        this.sm = sm;
        this.gj = gj;
        this.fy = fy;
        this.zl = zl;
        this.yz = yz;
        this.mj = mj;
    }

    public void createRandomParam() {
        this.sm = (int) (SM_MAX * Math.random());
        this.gj = (int) (GJ_MAX * Math.random());
        this.fy = (int) (FY_MAX * Math.random());
        this.zl = (int) (ZL_MAX * Math.random());
        this.yz = (int) (YZ_MAX * Math.random());
        this.mj = (int) (MJ_MAX * Math.random());
        Log.e("WuQiOld", "重新生成武器：" + toString());
    }

    public String getName() {
        return name;
    }

    public void setShenMing(int sm) {
        this.sm = sm;
    }

    public void setGongJi(int gj) {
        this.gj = gj;
    }

    public void setFangYu(int fy) {
        this.fy = fy;
    }

    public void setZhiLi(int zl) {
        this.zl = zl;
    }

    public void setYanZhi(int yz) {
        this.yz = yz;
    }

    public void setMinJie(int mj) {
        this.mj = mj;
    }

    @Override
    public int getTZ() {
        return 0;
    }

    @Override
    public int getSM() {
        return sm;
    }

    @Override
    public int getGJ() {
        return gj;
    }

    @Override
    public int getFY() {
        return fy;
    }

    @Override
    public int getZL() {
        return zl;
    }

    @Override
    public int getYZ() {
        return yz;
    }

    @Override
    public int getMJ() {
        return mj;
    }

    public double getSkillAdd() {
        return skillAdd;
    }

    public void setSkillAdd(double skillAdd) {
        this.skillAdd = skillAdd;
    }

    @Override
    public String toString() {
        return "ZhuangBei{" +
                "name='" + name + '\'' +
                ", SM=" + sm +
                ", GJ=" + gj +
                ", FY=" + fy +
                ", ZL=" + zl +
                ", YZ=" + yz +
                ", MJ=" + mj +
                '}';
    }

}

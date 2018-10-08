package com.raoqian.mobprosaleapplication.aa_game;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by raoqian on 2018/9/30.
 */

public class Person implements HumanParam {
    long id;
    int TZ;
    int SM;      //生命S*7
    int GJ;      //攻击G
    int FY;      //防御F
    int ZL;      //智力Z
    int YZ;      //颜值Y
    int MJ;      //敏捷M
    ZhuangBei zhuangBei = new ZhuangBei("随机装备");
    WuQiOld wuQiOld = new WuQiOld("随机武器");
    private double baojiCanShu = 2D;//暴击参数
    private double fanjiCanShu = 2D;//反击参数
    private int lastHurt = 0;
    private int lastDiyu = 0;
    public Map<String, Float> add = new HashMap<>();
    /**
     * 【十八掌】【攻击】发动几率20+YZ/10%,伤害N=GJ*（ZL-ZL+150）%                           降龙十八掌
     * 【千年杀】【攻击】削弱】发动几率20+YZ/10%,伤害N=GJ*（ZL-ZL+100）% 使对方失去10%YZ        千年杀
     * 【爱力量】【增强】发动几率25+YZ/10%,增加GJ+50                                        爱的力量
     * 【王霸气】【削弱】发动几率20+YZ/10%,削弱对方全体属性10%                                王霸之气
     * 【巧克力】【状态】使对方{毒}两回合                                                   情人节的巧克力
     * 【好人卡】【状态】使对方{发懵}两回合                                                  一张好人卡
     * 【八卦阵】【状态】使对方{错乱}两回合                                                  八卦迷阵
     */
    private List<String> studySkill = new ArrayList<>();//[]{"降龙十八掌", "千年杀"};
    public String lastStudySkill;
    private String statusValue;

    public Person(long id, int SM, int GJ, int FY, int ZL, int YZ, int MJ) {
        this.id = id;
        this.SM = SM;
        this.GJ = GJ;
        this.FY = FY;
        this.ZL = ZL;
        this.YZ = YZ;
        this.MJ = MJ;
        if (id == 1) {
            studySkill.add("降龙十八掌");
        } else {
            studySkill.add("千年杀");
        }
    }


    public Person(long id) {
        if (id == 1) {
            studySkill.add("降龙十八掌");
            studySkill.add("王霸之气");
        } else {
            studySkill.add("千年杀");
            studySkill.add("爱的力量");
        }
        this.id = id;
        int tiZhi = (int) Math.max(75, Math.random() * 155);
        int gongJi = (int) Math.max(75, Math.random() * 155);
        int fangYu = (int) Math.max(75, Math.random() * 155);
        int zhiLi = (int) Math.max(75, Math.random() * 155);
        int yanZhi = (int) Math.max(75, Math.random() * 155);
        int minJie = (int) Math.max(75, Math.random() * 155);
        int distanace = tiZhi + gongJi + fangYu + zhiLi + yanZhi + minJie - 600;
        int cut = distanace / 6;
        tiZhi = tiZhi - cut;
        gongJi = gongJi - cut;
        fangYu = fangYu - cut;
        zhiLi = zhiLi - cut;
        yanZhi = yanZhi - cut;
        minJie = minJie - cut;
        int less = 600 - (tiZhi + gongJi + fangYu + zhiLi + yanZhi + minJie);
        for (int i = 0; less > 0; i++, less--) {
            if (i == 0) {
                tiZhi++;
            } else if (i == 1) {
                gongJi++;
            } else if (i == 2) {
                fangYu++;
            } else if (i == 3) {
                yanZhi++;
            } else if (i == 4) {
                minJie++;
            }
        }
        this.SM = tiZhi * 7;
        this.TZ = tiZhi;
        this.GJ = gongJi;
        this.FY = fangYu;
        this.ZL = zhiLi;
        this.YZ = yanZhi;
        this.MJ = minJie;
        Log.e("Person", "生成角色：" + toString()
                + " \n总属性值 " + (tiZhi + gongJi + fangYu + zhiLi + yanZhi + minJie));
    }

//    public void setZhuangBei(ZhuangBei zhuangBei) {
////        this.zhuangBei.create(zhuangBei);
//        TLog.bean("Person", zhuangBei);
//        this.zhuangBei = zhuangBei;
//    }
//
//    public void setWuQiOld(WuQiOld wuQiOld) {
////        this.wuQiOld.create(wuQiOld);
//        TLog.bean("Person", wuQiOld);
//        this.wuQiOld = wuQiOld;
//    }

    public String setHurtIn(int value) {//受到伤害
//        this.lastDiyu = 0;// (int) (getFY() * Math.random());
//        if (value > 0) {
//            this.lastHurt = (value - lastDiyu);
//        } else {
//            this.lastHurt = value;
//        }
        this.SM -= value;//this.lastHurt;
        return "角色 " + id + " 受到 " + value + " 点伤害。";
    }

    @Override
    public int getTZ() {
        return this.TZ;
    }

    @Override
    public int getSM() {
        return SM;// + zhuangBei.getSM() + wuQiOld.getSM();
    }

    @Override
    public int getGJ() {
        return GJ;// + zhuangBei.getGJ() + wuQiOld.getGJ();
    }

    @Override
    public int getFY() {
        return FY;// + zhuangBei.getFY() + wuQiOld.getFY();
    }

    @Override
    public int getZL() {
        return ZL;// + zhuangBei.getZL() + wuQiOld.getZL();
    }

    @Override
    public int getYZ() {
        return YZ;//+ zhuangBei.getYZ() + wuQiOld.getYZ();
    }

    @Override
    public int getMJ() {
        return MJ;//+ zhuangBei.getMJ() + wuQiOld.getMJ();
    }

    /**
     * getC**()  获取Person本身属性，不参与计算
     */
    public int getCSM() {
        return SM;
    }

    public int getCGJ() {
        return GJ;
    }

    public int getCFY() {
        return FY;
    }

    public int getCZL() {
        return ZL;
    }

    public int getCYZ() {
        return YZ;
    }

    public int getCMJ() {
        return MJ;
    }

    public int getCTZ() {
        return MJ;
    }

    public void setCSM(int SM) {
        this.SM = SM;
    }

    public void setCGJ(int GJ) {
        this.GJ = GJ;
    }

    public void setCFY(int FY) {
        this.FY = FY;
    }

    public void setCZL(int ZL) {
        this.ZL = ZL;
    }

    public void setCYZ(int YZ) {
        this.YZ = YZ;
    }

    public void setCMJ(int MJ) {
        this.MJ = MJ;
    }

    public void setCTZ(int TZ) {
        this.TZ = TZ;
    }

    public int getBaojiValue() {
        return (int) (baojiCanShu * getGJ());
    }


    public int getFanjiValue() {
        return (int) (fanjiCanShu * getGJ());
    }


    private double RATE = 0.3;

    public boolean isFanJi(Person defender) {
        double ran = Math.random();
        return ran < (5 + (getMJ() - defender.getMJ()) / 5D) / 100D;
    }

    public boolean isBaoJi(Person defender) {//是否暴击
        return Math.random() < (7 + getZL() / 10D) / 100D;
    }

    public boolean isLianJj(Person defender) {
        return Math.random() < (10 + (getMJ() - defender.getMJ()) / 5D) / 100D;
    }

    public boolean isUseSkill(Person defender) {//是否用技能
        if (studySkill == null || studySkill.size() == 0) {
            return false;//没有技能
        }
        if (studySkill.contains("降龙十八掌")) {
            if (Math.random() < (20 + getYZ() / 10) / 100D) {
                lastStudySkill = "降龙十八掌";
                return true;
            }
        }
        if (studySkill.contains("千年杀")) {
            if (Math.random() < (20 + getYZ() / 10) / 100D) {
                lastStudySkill = "千年杀";
                return true;
            }
        }
        if (studySkill.contains("爱的力量")) {
            if (Math.random() < (25 + getYZ() / 10) / 100D) {
                lastStudySkill = "爱的力量";
                return true;
            }
        }
        return false;
    }

    public int getSkillHurt(Person defender, int index) {
        return BaseSkill.instance().getHurt(this, defender, index);
    }

    public String getStatusValue(int index) {
        return BaseSkill.instance().refuseStatus(this, index);
    }

    @Override
    public String toString() {
        return "\nPerson{" +
                "id=" + id +
                ",\n\t 生命值 ：" + this.SM +
                ",\n\t\t 攻击值 ：" + this.GJ +
                ",\n\t\t\t 防御值 ：" + this.FY +
                ",\n\t\t\t\t 智力值 ：" + this.ZL +
                ",\n\t\t\t\t\t 颜 值 ：" + this.YZ +
                ",\n\t\t\t\t\t\t 敏 捷 ：" + this.MJ +
                '}';
    }
}

package com.raoqian.mobprosaleapplication.aa_game;

import java.util.Map;

/**
 * Created by raoqian on 2018/10/6
 */

public class BaseSkill {
    private static BaseSkill mBaseSkill;

    private BaseSkill() {
    }

    public static BaseSkill instance() {
        if (mBaseSkill == null) {
            mBaseSkill = new BaseSkill();
        }
        return mBaseSkill;
    }

    /**
     * 0 - 无状态，主动技能
     * 【十八掌】【攻击】发动几率20+YZ/10%,伤害N=GJ*（ZL-ZL+150）%
     * 【千年杀】【攻击】削弱】发动几率20+YZ/10%,伤害N=GJ*（ZL-ZL+100）% 使对方失去10%YZ
     * 【爱力量】【增强】发动几率25+YZ/10%,增加GJ+50
     * 【王霸气】【削弱】发动几率20+YZ/10%,削弱对方全体属性10%
     * 【巧克力】【状态】使对方{毒}两回合
     * 【好人卡】【状态】使对方{发懵}两回合
     * 【八卦阵】【状态】使对方{错乱}两回合
     * <p>
     * 1 - { 毒 }行动前失去剩余生命值20%
     * 2 - {虚弱}失去80%攻击值，防御值
     * 3 - {发懵}失去80%智力值
     * 4 - {晕眩}，无法行动，，
     * 5 - {禁锢} 无法进行【普通攻击】
     * 6 - {错乱}进行攻击时75%几率攻击自己
     * 7 - {流血}失去全部生命值的15%
     * 8 - {洞穿}该回合失去全部防御力
     */
    public int getHurt(Person attacker, Person defender, int index) {

//【攻击】发动几率20+YZ/10%,伤害N=GJ*（ZL-ZL+150）%                           降龙十八掌
//【攻击】【削弱】发动几率20+YZ/10%,伤害N=GJ*（ZL-ZL+100）% 使对方失去10%YZ      千年杀
//【增强】发动几率25+YZ/10%,增加GJ+50                                        爱的力量
//【削弱】发动几率20+YZ/10%,削弱对方全体属性10%                                王霸之气
//【状态】使对方{毒}两回合                                                   情人节的巧克力
//【状态】使对方{发懵}两回合                                                  一张好人卡
//【状态】使对方{错乱}两回合                                                  八卦迷阵
        if ("降龙十八掌".equals(attacker.lastStudySkill)) {//GJ*（ZL-ZL+150）%
            return Math.max(0, (int) (attacker.getGJ() * (attacker.getZL() - defender.getZL() + 150) / 100D));
        }
        if ("千年杀".equals(attacker.lastStudySkill)) {
            defender.add.put("ADD_YZ_" + index, -0.1F);
            return Math.max(0, (int) (attacker.getGJ() * (attacker.getZL() - defender.getZL() + 100) / 100D));
        }
        if ("爱的力量".equals(attacker.lastStudySkill)) {
            attacker.add.put("ADD_GJ_" + index, 50F);
            return 0;
        }
        if ("王霸之气".equals(attacker.lastStudySkill)) {
            defender.add.put("ADD_GJ_" + index, -0.1F);
            defender.add.put("ADD_FY_" + index, -0.1F);
            defender.add.put("ADD_ZL_" + index, -0.1F);
            defender.add.put("ADD_YZ_" + index, -0.1F);
            defender.add.put("ADD_MJ_" + index, -0.1F);
            return 0;
        }
        if ("情人节的巧克力".equals(attacker.lastStudySkill)) {
            attacker.add.put("SET_DU_" + index, 2F);
            return 0;
        }
        if ("一张好人卡".equals(attacker.lastStudySkill)) {
            attacker.add.put("SET_FM_" + index, 2F);
            return 0;
        }
        if ("八卦迷阵".equals(attacker.lastStudySkill)) {
            attacker.add.put("SET_CL_" + index, 2F);
            return 0;
        }
        return 0;
    }

    public String refuseStatus(Person currentUser, int index) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Float> m : currentUser.add.entrySet()) {
            synchronized (BaseSkill.this) {
                String key = m.getKey();
                float value = m.getValue();
                String type = key.substring(0, 3);
                String which = key.substring(4, 6);
                String oldIndex = key.substring(7, key.length());
                sb.append("\n" + key + " -> " + value);
                float oldI = Float.parseFloat(oldIndex);
                if (value != 0) {
                    if ("ADD".equals(type)) {
                        if (value < 1 && value > -1) {
                            if ("SM".equals(which)) {
                                currentUser.setCSM((int) (currentUser.getCSM() * (1 + value)));
                            }
                            if ("GJ".equals(which)) {
                                currentUser.setCGJ((int) (currentUser.getCGJ() * (1 + value)));
                            }
                            if ("FY".equals(which)) {
                                currentUser.setCFY((int) (currentUser.getCFY() * (1 + value)));
                            }
                            if ("ZL".equals(which)) {
                                currentUser.setCZL((int) (currentUser.getCZL() * (1 + value)));
                            }
                            if ("YZ".equals(which)) {
                                currentUser.setCYZ((int) (currentUser.getCYZ() * (1 + value)));
                            }
                            if ("MJ".equals(which)) {
                                currentUser.setCMJ((int) (currentUser.getCMJ() * (1 + value)));
                            }
                        } else {
                            if ("SM".equals(which)) {
                                currentUser.setCSM((int) (currentUser.getCSM() + value));
                            }
                            if ("GJ".equals(which)) {
                                currentUser.setCGJ((int) (currentUser.getCGJ() + value));
                            }
                            if ("FY".equals(which)) {
                                currentUser.setCFY((int) (currentUser.getCFY() + value));
                            }
                            if ("ZL".equals(which)) {
                                currentUser.setCZL((int) (currentUser.getCZL() + value));
                            }
                            if ("YZ".equals(which)) {
                                currentUser.setCYZ((int) (currentUser.getCYZ() + value));
                            }
                            if ("MJ".equals(which)) {
                                currentUser.setCMJ((int) (currentUser.getCMJ() + value));
                            }
                        }
                        currentUser.add.put(key, 0F);
                    } else if ("SET".equals(type)) {
                        if ("DU".equals(which)) {
                            currentUser.setCSM((int) (currentUser.getCSM() * 0.8F));
                        }
                        if ("XR".equals(which)) {
                            currentUser.setCGJ((int) (currentUser.getCGJ() * 0.8F));
                            currentUser.setCFY((int) (currentUser.getCFY() * 0.8F));
                        }
                        if ("FM".equals(which)) {
                            currentUser.setCZL((int) (currentUser.getCZL() * 0.8F));
                        }
                        if ("LX".equals(which)) {
                            currentUser.setCSM((int) (currentUser.getSM() - currentUser.getCTZ() * 7 * 0.8F));
                        }
                        currentUser.add.put(key, value - 1);
                    }
                }
            }
        }
        return sb.append(currentUser.toString()).toString();
    }

    public boolean isXuanYuan(Person currentUser, int index) {
        for (Map.Entry<String, Float> m : currentUser.add.entrySet()) {
            synchronized (BaseSkill.this) {
                String key = m.getKey();
                float value = m.getValue();
                String type = key.substring(0, 3);
                String which = key.substring(4, 6);
                String oldIndex = key.substring(7, key.length());
                float oldI = Float.parseFloat(oldIndex);
                if (index * oldI > 0 && value != 0) {
                    if ("SET".equals(type)) {
                        if ("XY".equals(which)) {
                            currentUser.add.put(key, value - 1);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}

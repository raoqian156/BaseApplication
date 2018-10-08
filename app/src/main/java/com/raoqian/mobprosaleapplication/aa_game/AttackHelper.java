package com.raoqian.mobprosaleapplication.aa_game;


/**
 * Created by raoqian on 2018/10/6
 */

public class AttackHelper {
    private static AttackHelper helper;

    private AttackHelper() {

    }

    public static AttackHelper instance() {
        if (helper == null) {
            synchronized (AttackHelper.class) {
                if (helper == null) {
                    helper = new AttackHelper();
                }
            }
        }
        return helper;
    }

    /**
     * 【反击】被【普通攻击】时可以反击，免去此次受到的伤害并以正常攻击200%的伤害反击，发动几率为5+（敏捷M-敏捷M）/5%;
     * 【暴击】攻击时有一定几率获得200%的攻击力。发动几率7+智力Z/10%;
     * 【招架】对方的【普通攻击】无效，不造成伤害。发动几率10+（敏捷M-敏捷M）/5%与【反击】不同时发动;
     * 【连击】可以发动两次【普通攻击】，发动几率10+（敏捷M-敏捷M）/5%;
     */
    public String attack(Person attacker, Person defender, int index) {

        StringBuffer step = new StringBuffer();
        if (index > 0)
            step.append(attacker.getStatusValue(index) + defender.getStatusValue(index) + "\n");
        if (attacker.getSM() <= 0) {
            return "";
        }
        if (defender.getSM() <= 0) {
            return "";
        }
        if (BaseSkill.instance().isXuanYuan(attacker, index)) {
            return "角色 " + attacker.id + " 被眩晕了";
        }
        if (attacker.isUseSkill(defender)) {
            step.append("角色 " + attacker.id + " 使用学习到的技能" + attacker.lastStudySkill + "，");
            step.append(studySkill(attacker, defender, index));
        } else if (attacker.isLianJj(defender)) {
            step.append("角色 " + attacker.id + " 发动连击，");
            step.append(normalAttack(attacker, 1, defender));
            if (attacker.getSM() > 0 && defender.getSM() > 0) {
                step.append(normalAttack(attacker, 2, defender));
            }
        } else {
            step.append("角色 " + attacker.id + " 发动普通攻击，");
            step.append(normalAttack(attacker, 0, defender));
        }

        step.append("\n=====生命值情况=====1:" + (1 == attacker.id ? attacker.getSM() : defender.getSM()) + "    " +
                "2:" + (1 != attacker.id ? attacker.getSM() : defender.getSM()) + "=====");
        return step.toString() + "\n";
    }

    /**
     * @param attacker
     * @param i        攻击次数  0 非连击   1、2连击发生的顺序
     */
    private String normalAttack(Person attacker, int i, Person defender) {
        StringBuffer puji = new StringBuffer();
        if (i == 0) {
            if (attacker.isBaoJi(defender)) {
                puji.append("发生暴击，" + hurt(attacker, defender, true));
            } else {
                puji.append(hurt(attacker, defender, false));
            }
        } else {
            if (attacker.isBaoJi(defender)) {
                puji.append("第" + i + "击发生暴击，" + hurt(attacker, defender, true));
            } else {
                puji.append("第" + i + "击，" + hurt(attacker, defender, false));
            }
        }
        return puji.toString();
    }

    private String studySkill(Person attacker, Person defender, int index) {
        int hurt = attacker.getSkillHurt(defender, index);
        return defender.setHurtIn(hurt);
    }


    /**
     * 计算最终伤害
     */
    public String hurt(Person attacker, Person defender, boolean isBaoJi) {
        if (isBaoJi) {
            return defender.setHurtIn(attacker.getBaojiValue());
        } else if (defender.isFanJi(attacker)) {
            int gj = attacker.getFanjiValue();
            return "角色 " + defender.id + " 发生反击，" + attacker.setHurtIn(gj);
        }
        return defender.setHurtIn(attacker.getGJ());
    }
}

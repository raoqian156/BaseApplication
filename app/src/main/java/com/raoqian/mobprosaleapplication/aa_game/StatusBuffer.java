package com.raoqian.mobprosaleapplication.aa_game;

/**
 * Created by raoqian on 2018/10/9.
 */

public interface StatusBuffer {
//    { 毒 } 行动前失去剩余生命值20%
//    {虚弱}  失去80%攻击值，防御值
//    {发懵}  失去80%智力值
//    {晕眩}  无法行动
//    {禁锢}  无法进行【普通攻击】
//    {错乱}  进行攻击时75%几率攻击自己
//    {流血}  失去全部生命值的15%
//    {洞穿}  该回合失去全部防御力
    double smChangeBeforeStart();//行动前生命值基数（乘法运算）
    double gjChangeBeforeStart();//行动前攻击值基数（乘法运算）
    double fyChangeBeforeStart();//行动前防御值基数（乘法运算）
    double zlChangeBeforeStart();//行动前智力值基数（乘法运算）
    double yzChangeBeforeStart();//行动前颜值值基数（乘法运算）
    double mjChangeBeforeStart();//行动前敏捷值基数（乘法运算）

    double smAddBeforeStart();//行动前生命值变化数（直接 + ）
    double gjAddBeforeStart();//行动前攻击值变化数（直接 + ）
    double fyAddBeforeStart();//行动前防御值变化数（直接 + ）
    double zlAddBeforeStart();//行动前智力值变化数（直接 + ）
    double yzAddBeforeStart();//行动前颜值值变化数（直接 + ）
    double mjAddBeforeStart();//行动前敏捷值变化数（直接 + ）

    double XYRate();   //眩晕概率
    double JGRate();   //禁锢概率
    double CLRate();   //错乱概率
    double LXRate();   //流血概率
    double NFRate();   //抵御反击概率
    double NZRate();   //抵御招架概率
    double DCRate();   //洞穿概率
    double AARate();   //所有技能概率加成

}

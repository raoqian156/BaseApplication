package com.raoqian.mobprosaleapplication.aa_game;

/**
 * Created by raoqian on 2018/10/9.
 */

public class WUQI implements StatusBuffer, HumanParam {
    String name = "";
    int TZ = 0;
    int SM = 0;
    int GJ = 0;
    int FY = 0;
    int ZL = 0;
    int YZ = 0;
    int MJ = 0;
  double smAddBeforeStart=0;
  double         gjAddBeforeStart=0;
  double fyAddBeforeStart=0;
  double         zlAddBeforeStart=0;
  double yzAddBeforeStart=0;
  double         mjAddBeforeStart=0;

    double smChangeBeforeStart=1;
    double gjChangeBeforeStart=1;
    double fyChangeBeforeStart=1;
    double zlChangeBeforeStart=1;
    double yzChangeBeforeStart=1;
    double mjChangeBeforeStart=1;

    double XYRate=0;
    double JGRate=0;
    double CLRate=0;
    double LXRate=0;
    double NFRate=0;
    double NZRate=0;
    double DCRate=0;
    double AARate=0;


    public void setSM(int SM) {this.SM = SM;}
    public void setGJ(int GJ) {this.GJ = GJ;}
    public void setFY(int FY) {this.FY = FY;}
    public void setZL(int ZL) {this.ZL = ZL;}
    public void setYZ(int YZ) {this.YZ = YZ;}
    public void setMJ(int MJ) {this.MJ = MJ;}

    public void setName(String name) {
        this.name = name;
    }

    @Override public int getTZ() {return TZ;}
    @Override public int getSM() {return SM;}
    @Override public int getGJ() {return GJ;}
    @Override public int getFY() {return FY;}
    @Override public int getZL() {return ZL;}
    @Override public int getYZ() {return YZ;}
    @Override public int getMJ() {return MJ;}

    @Override public double smChangeBeforeStart() {return  smChangeBeforeStart;}
    @Override public double gjChangeBeforeStart() {return  gjChangeBeforeStart;}
    @Override public double fyChangeBeforeStart() {return  fyChangeBeforeStart;}
    @Override public double zlChangeBeforeStart() {return  zlChangeBeforeStart;}
    @Override public double yzChangeBeforeStart() {return  yzChangeBeforeStart;}
    @Override public double mjChangeBeforeStart() {return  mjChangeBeforeStart;}

    @Override public double smAddBeforeStart() {return smAddBeforeStart;}
    @Override public double gjAddBeforeStart() {return gjAddBeforeStart;}
    @Override public double fyAddBeforeStart() {return fyAddBeforeStart;}
    @Override public double zlAddBeforeStart() {return zlAddBeforeStart;}
    @Override public double yzAddBeforeStart() {return yzAddBeforeStart;}
    @Override public double mjAddBeforeStart() {return mjAddBeforeStart;}

    @Override public double XYRate() {return 0;}
    @Override public double JGRate() {return 0;}
    @Override public double CLRate() {return 0;}
    @Override public double LXRate() {return 0;}
    @Override public double NFRate() {return 0;}
    @Override public double NZRate() {return 0;}
    @Override public double DCRate() {return 0;}
    @Override public double AARate() {return 0;}

}

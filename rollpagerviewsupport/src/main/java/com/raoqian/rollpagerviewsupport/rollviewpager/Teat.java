package com.raoqian.rollpagerviewsupport.rollviewpager;

/**
 * Created by Administrator on 2018/6/3.
 */

public class Teat {
    public static void main(String[] arg) {
        int test = 194;
        System.out.println(getLocationRB(test));
        System.out.println(getLocationLT(test));
    }

    public static int getLocationLT(int location) {
        int lt = 9;
        for (int com = location, res = location % 10; com > 0; com = com / 10, res = com % 10) {
            lt = Math.min(lt, res);
        }
        return lt;
    }

    public static int getLocationRB(int location) {
        int lt = 1;
        for (int com = location, res = location % 10; com > 0; com = com / 10, res = com % 10) {
            lt = Math.max(lt, res);
        }
        return lt;
    }

}

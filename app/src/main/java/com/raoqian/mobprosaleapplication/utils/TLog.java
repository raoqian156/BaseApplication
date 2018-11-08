package com.raoqian.mobprosaleapplication.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

@SuppressLint("LongLogTag")
public class TLog {
    public static final String LOG_TAG = "TLog";
    public static boolean DEBUG = true;//是否处在debug

    public TLog() {
    }

    public static final void analytics(String log) {
        if (DEBUG)
            Log.d(LOG_TAG, log);
    }

    public static final void error(String log) {
        if (DEBUG)
            Log.e(LOG_TAG, "" + log);
    }

    public static final void error(String tag, String log) {
        if (DEBUG)
            Log.e(tag, "" + log);
    }

    public static final void bean(String tag, Object obj) {
        try {
            bean(tag, new Gson().toJson(obj).trim());
        } catch (Exception e) {
            Log.w("TLog", "bean: 输出错误");
        }
    }

    public static final void pBean(String tag, Object obj) {
        pBean(tag, new Gson().toJson(obj).trim());
    }

    private static void pBean(String tag, String log) {
        if (DEBUG) {
            Log.d(tag, "------------------------------" + tag + "------------------------------");
            Log.d(tag, "=====>" + log);
            String outPut = log.replaceAll(":\\{", ":,{").replaceAll(":\\[\\{", ":[,{")
                    .replaceAll("\\}", "},").replaceAll("\\]", "],").replaceAll("\\\\\"", "");
            TLog.error(tag, "=====>" + outPut);
            String[] outs = outPut.split(",");
            String SPACE = "";
            for (int i = 0; i < outs.length; i++) {
//                if (outs[i].endsWith("null")) {
//                    continue;
//                }
                if (outs[i].contains("{") || outs[i].contains("[")) {
                    SPACE = SPACE + "\t";
                } else if (outs[i].contains("}") || outs[i].contains("]")) {
                    if (!TextUtils.isEmpty(SPACE)) {
                        SPACE = SPACE.substring(0, SPACE.lastIndexOf("\t"));
                    }
                }
                Log.d(tag, "    --" + i + "->" + SPACE + outs[i]);
            }
            Log.d(tag, "------------------------------" + tag + ".end------------------------------");
        }
    }

    /**
     * 0 指前面补充零
     * formatLength 字符总长度为 formatLength
     * d 代表为正数。
     */
    public static String frontCompWithZore(int sourceDate, int formatLength) {
        String newString = String.format("%0" + formatLength + "d", sourceDate);
        return newString;
    }

    private static void bean(String tag, String log) {
        if (DEBUG) {
            Log.w("RQ." + tag, "------------------------------" + tag + "------------------------------");
            Log.w("RQ." + tag, "=====>" + log);
            String outPut = log.replaceAll(":\\{", ":,{").replaceAll(":\\[\\{", ":[,{")
                    .replaceAll("\\}", "},").replaceAll("\\]", "],").replaceAll("\\\\\"", "");
            String[] outs = outPut.split(",");
            String SPACE = "";
            int lineNumLength = String.valueOf(outs.length).length();
            for (int i = 0; i < outs.length; i++) {
                String showLine = frontCompWithZore(i, lineNumLength);
                if (outs[i].contains("}") || outs[i].contains("]")) {
                    if (!TextUtils.isEmpty(SPACE)) {
                        SPACE = SPACE.substring(0, SPACE.lastIndexOf("\t"));
                    }
                }
                Log.w("RQ." + tag, "    --" + showLine + "->" + SPACE + outs[i]);
                if (outs[i].startsWith("{") || outs[i].contains("[")) {
                    SPACE = SPACE + "\t";
                }
            }
            Log.w("RQ." + tag, "------------------------------" + tag + ".end------------------------------");
        }
    }

    public static final void tagLog(String tag, String log) {
        if (DEBUG) {
            Log.e(tag, "------------------------------" + tag + "------------------------------");
            Log.e(tag, log);
            Log.e(tag, "------------------------------" + tag + ".end--------------------------");
        }
    }

    public static final void log(String log) {
        if (DEBUG) {
            Log.e(LOG_TAG, log);
        }
    }

    public static final void log(String... log) {
        if (DEBUG) {
            StringBuilder sb = new StringBuilder();
            for (String logI : log) {
                sb = sb.append("     ").append(logI);
            }
            log(sb.toString());
        }
    }

    public static final void logI(String log) {
        if (DEBUG)
            Log.i(LOG_TAG, log);
    }

    public static final void warn(String log) {
        if (DEBUG)
            Log.w(LOG_TAG, log);
    }

    public static final void warn(String tag, String log) {
        if (DEBUG)
            Log.w(tag, log);
    }

    public static void e(String tag, String[] strings, String method) {
        if (strings != null && strings.length > 0) {
            String content = "";
            for (int i = 0; i < strings.length; i++) {
                content += "  " + strings[i];
            }
            Log.e(tag, method + " : " + content);
        }
    }
}

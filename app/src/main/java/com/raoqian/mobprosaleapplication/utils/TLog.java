package com.raoqian.mobprosaleapplication.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.raoqian.mobprosaleapplication.log_util.Logger2;

@SuppressLint("LongLogTag")
public class TLog {
    public static final String LOG_TAG = "com.raoqian.mobprosaleapplication.utils.TLog";
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
        bean(tag, new Gson().toJson(obj).trim());
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

    private static void bean(String tag, String log) {
        if (DEBUG) {
            Log.e(tag, "------------------------------" + tag + "------------------------------");
            Log.e(tag, "=====>" + log);
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
                Log.w(tag, "    --" + i + "->" + SPACE + outs[i]);
            }
            Log.e(tag, "------------------------------" + tag + ".end------------------------------");
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

    public static void lineHere() {
        Logger2.d("aa");
    }

    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(Line:%d)";
//        占位符
        String callerClazzName = caller.getClassName();
//        获取到类名
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
//        替换
        return tag;
    }

//    StackTraceElement[] stackTrace;
//    private synchronized StackTraceElement[] getOurStackTrace() {
//        // Initialize stack trace field with information from
//        // backtrace if this is the first call to this method
//        //
//        // Android-changed: test explicitly for equality with
//        // STACK_TRACE_ELEMENT
//        if (stackTrace == libcore.util.EmptyArray.STACK_TRACE_ELEMENT ||
//                (stackTrace == null && backtrace != null) /* Out of protocol state */) {
//
//            backtrace = null;
//        }
//
//        // Android-changed: Return an empty element both when the stack trace
//        // isn't writeable and also when nativeGetStackTrace returns null.
//        if (stackTrace == null) {
//            return libcore.util.EmptyArray.STACK_TRACE_ELEMENT;
//        }
//
//        return stackTrace;
//    }
}

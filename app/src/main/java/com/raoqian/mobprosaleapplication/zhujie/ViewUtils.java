package com.raoqian.mobprosaleapplication.zhujie;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/5/15.
 */

public class ViewUtils {

    static class ViewFinder {
        Object obj;
        Activity activity;
        View view;

        public ViewFinder(Object obj, Activity activity) {
            this.obj = obj;
            this.activity = activity;
            this.view = null;
        }

        public ViewFinder(Object obj, View view) {
            this.obj = obj;
            this.view = view;
            this.activity = null;
        }

        public View findViewById(int viewId) {
            if (activity != null) {
                return activity.findViewById(viewId);
            } else {
                return view.findViewById(viewId);
            }
        }
    }

    public static void inject(final Activity activity) {
        Class<?> clazz = activity.getClass();
        findViewById(new ViewFinder(activity, activity), clazz);
        setOnClick(new ViewFinder(activity, activity), clazz);
    }

    public static void inject(Fragment fragment, final View activity) {
        Class<?> clazz = activity.getClass();
        findViewById(new ViewFinder(fragment, activity), clazz);
        setOnClick(new ViewFinder(fragment, activity), clazz);
    }
    public static void inject(android.support.v4.app.Fragment fragment, final View activity) {
        Class<?> clazz = activity.getClass();
        findViewById(new ViewFinder(fragment, activity), clazz);
        setOnClick(new ViewFinder(fragment, activity), clazz);
    }

    private static void setOnClick(final ViewFinder activity, Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (final Method method : methods) {
            OnClick onClick = method.getAnnotation(OnClick.class);
            if (onClick != null) {
                int[] viewIds = onClick.value();
                for (int viewId : viewIds) {
                    View view = activity.findViewById(viewId);
                    method.setAccessible(true);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                method.invoke(activity.obj);
                            } catch (Exception e) {
//                               e.printStackTrace();
                                try {
                                    method.invoke(activity.obj, v);
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    private static void findViewById(ViewFinder activity, Class<?> clazz) {
        //1.获取对象所有属性
        //clazz.getFields()  获取公共属性 public
        Field[] params = clazz.getDeclaredFields();//获取所有属性
        //2.判断属性是否有对应的注解
        for (Field field : params) {
            //判断是否含有相应注解
            ViewById viewById = field.getAnnotation(ViewById.class);
            if (viewById != null) {
                //有相应注解
                //获取注解后注入的参数
                int viewId = viewById.value();
                //3.调用相应方法进行相应操作
                View view = activity.findViewById(viewId);
                //3.1  完成赋值
                try {
                    field.setAccessible(true);//私有属性公有化??
                    field.set(activity.obj, view);//只能调用共有变量-没有上一行会报错
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package com.raoqian.mobprosaleapplication.zhujie;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Created by Administrator on 2018/5/15.
 */

public class ReadUtils {

    public static void inject(final Object obj) {
        Class<?> clazz = obj.getClass();
        setOnClick(obj, clazz);
    }

    private static void setOnClick(final Object obj, Class<?> clazz) {

        Field[] params = clazz.getDeclaredFields();//获取所有属性
        for (Field field : params) {
            //判断是否含有相应注解
            ReadFile readFile = field.getAnnotation(ReadFile.class);
            if (readFile != null) {
                //有相应注解
                //获取注解后注入的参数

                Class<?> clz = field.getType();
                //3.调用相应方法进行相应操作
                field.setAccessible(true);
                try {
                    Object data = clz.newInstance();
                    String[] pragm = readFile.value();
                    Field[] dataParams = data.getClass().getDeclaredFields();//获取所有属性
                    String finalUrl = pragm[0];

                    Properties properties = new Properties();
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(finalUrl));
                    properties.load(bufferedReader);
                    for (int i = 0; i < dataParams.length; i++) {
                        for (int j = 0; j < pragm.length; j++) {
                            if (j != 0) {
                                String p = pragm[j].replace(".", "");
                                if (dataParams[i].getName().equalsIgnoreCase(p)) {
                                    if ("int".equals(dataParams[i].getType().toString())) {
                                        dataParams[i].set(data, Integer.parseInt(properties.getProperty(pragm[j])));
                                    } else {
                                        dataParams[i].set(data, properties.getProperty(pragm[j]));
                                    }
                                }
                            }
                        }

                    }
//                    for (int i = 0; i < 2; i++) {
//                        if (i == 0) {
//                            dataParams[0].set(data, properties.getProperty(old1));
//                        } else {
//                            dataParams[1].set(data, properties.getProperty(old2));
//                        }
//                    }
                    Log.e("ReadUtils", "data = " + data.toString());
//                    for (Field dataParam : dataParams) {
//                        dataParam.set(data,properties.getProperty(old1) );
//                        Log.e("ReadUtils", "dataParam -> " + dataParam);
//                    }

                    field.set(obj, data);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

//        public static Properties readFile(String fileUrl) throws IOException {
//            Properties properties = new Properties();
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileUrl));
//            properties.load(bufferedReader);
//            return properties;
//        }
//
//        public static void main(String[] args) throws IOException {
//            Properties properties = CC.readFile("E:\\AndroidCode\\MobProSaleApplication\\local.properties");
//            System.out.println(properties.getProperty("ndk.dir"));
//        }

//
//        Method[] methods = clazz.getDeclaredMethods();
//        for (final Method method : methods) {
//            ReadFile onClick = method.getAnnotation(ReadFile.class);
//            if (onClick != null) {
//                String url = onClick.value();
//                method.setAccessible(true);
//                //3.调用相应方法进行相应操作
////                View view = obj.findViewById(viewId);
////                //3.1  完成赋值
//                Field f = null;
//                try {
//                    f = obj.getClass().getDeclaredField("readOnly");
//                    f.setAccessible(true);
//                    f.set(obj, "test");
//                } catch (NoSuchFieldException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
    }
//
//    private static void findViewById(Activity obj, Class<?> clazz) {
//        //1.获取对象所有属性
//        //clazz.getFields()  获取公共属性 public
//        Field[] params = clazz.getDeclaredFields();//获取所有属性
//        //2.判断属性是否有对应的注解
//        for (Field field : params) {
//            //判断是否含有相应注解
//            ViewById viewById = field.getAnnotation(ViewById.class);
//            if (viewById != null) {
//                //有相应注解
//                //获取注解后注入的参数
//                int viewId = viewById.value();
//                //3.调用相应方法进行相应操作
//                View view = obj.findViewById(viewId);
//                //3.1  完成赋值
//                try {
//                    field.setAccessible(true);//私有属性公有化??
//                    field.set(obj, view);//只能调用共有变量-没有上一行会报错
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}

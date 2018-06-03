package com.raoqian.mobprosaleapplication.zhujie;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018/5/15.
 */

@Target(ElementType.FIELD)//注解使用位置 Filed 属性注解 Method 方法注解  Type 类注解
@Retention(RetentionPolicy.CLASS)//注解生效时间
public @interface ReadFileP {
    Class<?> value();//该注解后面可传参数类型
}

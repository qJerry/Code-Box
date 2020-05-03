package com.github.qjerry;

import java.lang.annotation.*;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: 业务错误码枚举类注解，注解的枚举类将会动态加载到SDK的枚举类中，并检测错误码内容</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/18
 */
@Documented
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface CodeBoxData {

    int sort() default 0;;
}

package com.github.qjerry.enums;

import com.github.qjerry.CodeBoxData;
import lombok.Getter;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/19
 */
@Getter
@CodeBoxData
public enum BusinessCodeEnum {

    // 设备：1000-1999
    EQUIPMENT_NOT_EXIST(1000, "设备不存在", ""),

    // 用户：2000-2499
    USER_LOGIN_AGAIN(2000, "请重新登录", "当前缓存信息已过期，需要重新登录"),
    ;

    Integer code;
    String message;
    String detail;

    BusinessCodeEnum(Integer code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

}

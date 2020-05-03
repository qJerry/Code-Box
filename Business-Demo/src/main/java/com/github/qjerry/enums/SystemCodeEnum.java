package com.github.qjerry.enums;

import com.github.qjerry.CodeBoxData;
import lombok.Getter;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/18
 */
@Getter
//@CodeBoxData
public enum SystemCodeEnum {

    PARAM_ERROR(101, "参数有误", "请检查入参：必填参数是否为空，长度超出规定限制长度 或 是否不符合格式"),
    SYSTEM_ERROR(102, "系统繁忙", "可能发生了网络或者系统异常"),
    SERVER_BUSY(200, "服务繁忙", "系统服务出小差了，请稍后重试");

    Integer code;
    String message;
    String detail;

    SystemCodeEnum(Integer code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }
}

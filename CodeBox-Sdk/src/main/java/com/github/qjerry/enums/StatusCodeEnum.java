package com.github.qjerry.enums;

import com.github.qjerry.dto.StatusCodeDTO;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: 状态码枚举类</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/3/30
 */
@Getter
public enum StatusCodeEnum {

    // 动态插入枚举数据
    CODE_0(0L, "成功", "成功");

    Long code;
    String message;
    String detail;

    StatusCodeEnum(Long code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    public static StatusCodeDTO getStatusCode(Long code) {
        return Arrays.stream(StatusCodeEnum.values()).filter(s -> s.getCode().equals(code)).findFirst().map(m -> StatusCodeDTO.create(m.getCode(), m.getMessage(), m.getDetail())).orElse(StatusCodeDTO.create(StatusCodeEnum.CODE_0.getCode(), StatusCodeEnum.CODE_0.getMessage(), StatusCodeEnum.CODE_0.getDetail()));
    }

    public static String getValues() {
        return Arrays.stream(StatusCodeEnum.values()).map(m -> m.message).collect(Collectors.joining(","));
    }

}

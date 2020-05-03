package com.github.qjerry.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/3/29
 */
@Getter
public enum ApplyEnums {

    APPLYING(-1, "申请中", "错误码[%s]-[%s]配置申请中"),
    SUCCESS(0, "申请通过", ""),
    FAILED(1, "申请不通过", "错误码[%s]-[%s]配置申请失败，失败原因：%s");

    int code;
    String name;
    String desc;

    ApplyEnums(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    /**
     * 获取描述信息
     * @param code
     * @param args
     * @return
     */
    public static String getDesc(int code, Object... args) {
        return Arrays.stream(ApplyEnums.values()).filter(a -> a.getCode() == code).findFirst().map(a -> String.format(a.getDesc(), args)).get();
    }
}

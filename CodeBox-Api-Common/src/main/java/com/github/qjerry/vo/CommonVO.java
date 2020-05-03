package com.github.qjerry.vo;

import lombok.Data;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: 通用出参</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/2
 */
@Data
public class CommonVO<T> {

    private Integer code;

    private String message;

    private String details;

    private T data;

    public static CommonVO create(Integer code, String message) {
        CommonVO vo = new CommonVO();
        vo.setCode(code);
        vo.setMessage(message);
        return vo;
    }

    public static CommonVO err(String message) {
        CommonVO vo = new CommonVO();
        vo.setCode(-1);
        vo.setMessage(message);
        return vo;
    }

    public static CommonVO suc() {
        CommonVO vo = new CommonVO();
        vo.setCode(0);
        return vo;
    }

    public static CommonVO suc(Object data) {
        CommonVO vo = new CommonVO();
        vo.setCode(0);
        vo.setData(data);
        return vo;
    }

    public static CommonVO suc(String message, Object data) {
        CommonVO vo = new CommonVO();
        vo.setCode(0);
        vo.setMessage(message);
        vo.setData(data);
        return vo;
    }
}

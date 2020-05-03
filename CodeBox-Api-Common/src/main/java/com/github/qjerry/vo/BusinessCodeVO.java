package com.github.qjerry.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: 错误码出参</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/14
 */
@Data
public class BusinessCodeVO extends BusinessSystemVO implements Serializable {

    private static final long serialVersionUID = -2795087685541629033L;

    /**
     * 错误码
     */
    private Long code;

    /**
     * 业务消息
     */
    private String message;

    /**
     * 错误详细内容，包括解决方案等
     */
    private String detail;

    public BusinessCodeVO(Long code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }
}

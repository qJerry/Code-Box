package com.github.qjerry.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: json错误码实体</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/3/30
 */
@Data
public class StatusCodeDTO implements Serializable {

    private static final long serialVersionUID = 7780848736793050571L;

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

    public static StatusCodeDTO create(Long code, String message, String detail) {
        StatusCodeDTO statusCodeDTO = new StatusCodeDTO();
        statusCodeDTO.setCode(code);
        statusCodeDTO.setMessage(message);
        statusCodeDTO.setDetail(detail);
        return statusCodeDTO;
    }
}

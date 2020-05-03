package com.github.qjerry.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/13
 */
@Data
public class CodeDTO implements Serializable {

    private static final long serialVersionUID = 7872633895374065743L;

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

    public static CodeDTO create(Long code, String message, String detail) {
        CodeDTO codeDTO = new CodeDTO();
        codeDTO.setCode(code);
        codeDTO.setMessage(message);
        codeDTO.setDetail(detail);
        return codeDTO;
    }
}

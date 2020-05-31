package com.github.qjerry.exception;

import com.github.qjerry.dto.StatusCodeDTO;
import com.github.qjerry.utils.CodeUtils;
import lombok.Data;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: 码盒全局异常</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/2
 */
@Data
public class CodeBoxGlobalException extends RuntimeException {

    private Long code;
    private String message;
    private String detail;

    public CodeBoxGlobalException(Long code) {
        super(code + "");
        StatusCodeDTO statusCodeDTO = CodeUtils.getCode(code);
        this.code = code;
        this.message = statusCodeDTO.getMessage();
        this.detail = statusCodeDTO.getDetail();
    }

    public CodeBoxGlobalException(String message) {
        super(message);
        this.message = message;
    }
}

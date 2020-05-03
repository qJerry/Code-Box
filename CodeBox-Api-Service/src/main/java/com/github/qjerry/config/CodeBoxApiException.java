package com.github.qjerry.config;

import lombok.Data;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/2
 */
@Data
public class CodeBoxApiException extends Exception {

    private Integer code;
    private String message;

    public CodeBoxApiException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public CodeBoxApiException(String message) {
        super(message);
        this.message = message;
    }
}

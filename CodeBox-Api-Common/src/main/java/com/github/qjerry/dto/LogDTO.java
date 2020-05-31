package com.github.qjerry.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title:API-ERRCODE</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/5/13
 */
@Data
public class LogDTO extends BusinessSystemDTO implements Serializable {

    /**
     * 日志内容
     */
    private String content;
}

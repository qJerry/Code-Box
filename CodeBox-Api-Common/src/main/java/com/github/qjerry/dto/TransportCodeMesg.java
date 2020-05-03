package com.github.qjerry.dto;

import lombok.Data;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: 传输的错误码内容，用于队列上的数据传输体</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/21
 */
@Data
public class TransportCodeMesg {

    /**
     * 错误码
     */
    private Long code;

    /**
     * 新修改的错误码内容
     */
    private String changeMessage;
}

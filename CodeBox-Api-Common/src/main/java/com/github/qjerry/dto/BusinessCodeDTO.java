package com.github.qjerry.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/16
 */
@Data
public class BusinessCodeDTO extends BusinessSystemDTO implements Serializable {

    /**
     * 错误码
     */
    public List<CodeDTO> codes;
}

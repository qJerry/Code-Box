package com.github.qjerry.vo;

import com.github.qjerry.dto.StatusCodeDTO;
import com.github.qjerry.utils.CodeUtils;
import lombok.Data;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: SDK通用出参</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/2
 */
@Data
public class ResultVO<T> {

    private Long code;

    private String message;

    private String details;

    private T data;

    public static ResultVO create(Long code, String message) {
        ResultVO vo = new ResultVO();
        vo.setCode(code);
        vo.setMessage(message);
        return vo;
    }

    public static ResultVO err(String message) {
        ResultVO vo = new ResultVO();
        vo.setCode(-1L);
        vo.setMessage(message);
        return vo;
    }

    public static ResultVO err(Long code) {
        ResultVO vo = new ResultVO();
        StatusCodeDTO statusCodeDTO = CodeUtils.getCode(code);
        vo.setCode(statusCodeDTO.getCode());
        vo.setMessage(statusCodeDTO.getMessage());
        vo.setDetails(statusCodeDTO.getDetail());
//        vo.setMessage(StatusCodeEnum.message(code));
        return vo;
    }

    public static ResultVO suc() {
        ResultVO vo = new ResultVO();
        vo.setCode(0L);
        return vo;
    }

    public static ResultVO suc(Object data) {
        ResultVO vo = new ResultVO();
        vo.setCode(0L);
        vo.setData(data);
        return vo;
    }

    public static ResultVO suc(String message, Object data) {
        ResultVO vo = new ResultVO();
        vo.setCode(0L);
        vo.setMessage(message);
        vo.setData(data);
        return vo;
    }
}

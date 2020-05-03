package com.github.qjerry.config;

import com.github.qjerry.vo.CommonVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/10
 */
@Slf4j
@ControllerAdvice
public class WebErrorHandler {

    @ExceptionHandler(value = CodeBoxApiException.class)
    @ResponseBody
    public CommonVO errorHandler(CodeBoxApiException e) {
        log.error("BizCodeException {}" , e.getMessage());
        return CommonVO.create(-1 , e.getMessage());
    }

}

package com.github.qjerry.exception;

import com.github.qjerry.config.CodeBoxConfig;
import com.github.qjerry.event.CodeLogEvent;
import com.github.qjerry.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: 码盒全局异常处理器</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/10
 */
@Slf4j
@ControllerAdvice
public class WebErrorHandler {

    @Autowired
    ApplicationContext context;

    @ExceptionHandler(value = CodeBoxGlobalException.class)
    @ResponseBody
    public ResultVO errorHandler(CodeBoxGlobalException e) {
        log.error("CodeBoxGlobalException: {}" , e.getMessage());
        ResultVO vo = new ResultVO();
        vo.setCode(e.getCode());
        vo.setMessage(e.getMessage());
        vo.setDetails(e.getDetail());
        if(CodeBoxConfig.syncLog) {
            context.publishEvent(new CodeLogEvent(e));
        }
        return vo;
    }

}

package com.github.qjerry.event;

import com.github.qjerry.exception.CodeBoxGlobalException;
import com.github.qjerry.vo.ResultVO;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * <p>Title:API-ERRCODE</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/5/31
 */
@Getter
public class CodeLogEvent extends ApplicationEvent {

    private CodeBoxGlobalException exception;

    public CodeLogEvent(CodeBoxGlobalException exception) {
        super(exception.getCode());
        this.exception = exception;
    }

}

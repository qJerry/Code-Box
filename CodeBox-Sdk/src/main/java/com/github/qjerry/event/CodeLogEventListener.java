package com.github.qjerry.event;

import com.alibaba.fastjson.JSON;
import com.github.qjerry.config.CodeBoxConfig;
import com.github.qjerry.dto.LogDTO;
import com.github.qjerry.exception.CodeBoxGlobalException;
import com.github.qjerry.service.CodeBoxApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * <p>Title:API-ERRCODE</p>
 * <p>Desc: 错误码日志同步到码盒后台</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/5/31
 */
@EnableAsync
@Component
public class CodeLogEventListener implements ApplicationListener<CodeLogEvent> {

    @Autowired
    private CodeBoxApiService codeBoxApiService;

    @Async
    @Override
    public void onApplicationEvent(CodeLogEvent codeLogEvent) {
        CodeBoxGlobalException exception = codeLogEvent.getException();
        LogDTO logDTO = new LogDTO();
        logDTO.setBusinessId(CodeBoxConfig.businessId);
        logDTO.setSystemId(CodeBoxConfig.businessSystemId);
        logDTO.setContent(exception.getCode() + ": " + exception.getMessage() + "\n" + JSON.toJSONString(exception.getStackTrace()));
        codeBoxApiService.addLog(logDTO);
    }
}

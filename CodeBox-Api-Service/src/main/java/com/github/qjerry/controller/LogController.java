package com.github.qjerry.controller;

import com.github.qjerry.config.CodeBoxApiException;
import com.github.qjerry.dto.BusinessCodeDTO;
import com.github.qjerry.dto.LogDTO;
import com.github.qjerry.service.LogService;
import com.github.qjerry.vo.CommonVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title:API-ERRCODE</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/5/13
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController extends BaseController {

    @Autowired
    LogService logService;

    /**
     * 同步错误日志
     * @param logDTO
     * @return
     * @throws CodeBoxApiException
     */
    @RequestMapping("/sync")
    public CommonVO add(@RequestBody LogDTO logDTO) throws CodeBoxApiException {
        return success(logService.addLog(logDTO));
    }

}

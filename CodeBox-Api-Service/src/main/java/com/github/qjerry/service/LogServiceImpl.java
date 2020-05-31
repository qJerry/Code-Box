package com.github.qjerry.service;

import com.github.qjerry.dto.LogDTO;
import com.github.qjerry.entity.AdBusinessCodeLog;
import com.github.qjerry.mapper.AdBusinessCodeLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>Title:API-ERRCODE</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/5/13
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    AdBusinessCodeLogMapper codeLogMapper;

    @Override
    public int addLog(LogDTO logDTO) {
        AdBusinessCodeLog errorLog = new AdBusinessCodeLog();
        errorLog.setAdBusinessId(logDTO.getBusinessId());
        errorLog.setAdBusinessSystemId(logDTO.getSystemId());
        errorLog.setContent(logDTO.getContent());
        errorLog.setCreated(LocalDateTime.now());
        errorLog.setCreatedby(logDTO.getBusinessId());
        return codeLogMapper.insert(errorLog);
    }
}

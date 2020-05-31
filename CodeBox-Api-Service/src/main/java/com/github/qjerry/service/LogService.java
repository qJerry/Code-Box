package com.github.qjerry.service;

import com.github.qjerry.dto.LogDTO;

/**
 * <p>Title:API-ERRCODE</p>
 * <p>Desc: 日志服务类</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/5/13
 */
public interface LogService {

    int addLog(LogDTO logDTO);
}

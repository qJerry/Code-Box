package com.github.qjerry.service;

import com.github.qjerry.config.CodeBoxApiException;
import com.github.qjerry.vo.BusinessSystemVO;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: 业务部门、系统服务</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/3
 */
public interface BusinessService {

    /**
     * 获取业务系统信息
     * @param businessId
     * @param systemId
     * @return
     */
    BusinessSystemVO getBusinessSystem(Long businessId, Long systemId) throws CodeBoxApiException;

    /**
     * 获取系统信息
     * @param systemId
     * @return
     * @throws CodeBoxApiException
     */
    BusinessSystemVO getSystem(Long systemId) throws CodeBoxApiException;
}

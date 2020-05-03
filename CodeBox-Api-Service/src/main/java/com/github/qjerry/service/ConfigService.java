package com.github.qjerry.service;

import com.github.qjerry.config.CodeBoxApiException;
import com.github.qjerry.vo.BusinessConfigVO;
import com.github.qjerry.vo.BusinessCodeConfigVO;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: 申请配置服务</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/3
 */
public interface ConfigService {

    /**
     * 获取申请配置
     * @param businessId
     * @param systemId
     * @return
     */
    BusinessConfigVO getApplyConfig(Long businessId, Long systemId) throws CodeBoxApiException;

    /**
     * 获取申请的统一码范围
     * @param businessId
     * @param systemId
     * @return
     * @throws CodeBoxApiException
     */
    BusinessCodeConfigVO getApplyBizcodeRange(Long businessId, Long systemId) throws CodeBoxApiException;
}

package com.github.qjerry.service;

import com.github.qjerry.dto.BusinessCodeDTO;
import com.github.qjerry.dto.BusinessSystemDTO;
import com.github.qjerry.config.CodeBoxApiException;
import com.github.qjerry.vo.BusinessCodeVO;

import java.util.List;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: 错误码服务</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/13
 */
public interface CodeService {

    /**
     * 添加或修改错误码
     * @param businessCodeDTO
     * @return
     * @throws CodeBoxApiException
     */
    int saveOrUpdateCode(BusinessCodeDTO businessCodeDTO) throws CodeBoxApiException;

    /**
     * 获取错误码内容
     * @param businessSystemDTO
     * @return
     */
    List<BusinessCodeVO> getBusinessCode(BusinessSystemDTO businessSystemDTO) throws CodeBoxApiException;
    
}

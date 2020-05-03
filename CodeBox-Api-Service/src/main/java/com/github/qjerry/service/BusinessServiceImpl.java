package com.github.qjerry.service;

import com.github.qjerry.entity.AdBusiness;
import com.github.qjerry.entity.AdBusinessSystem;
import com.github.qjerry.config.CodeBoxApiException;
import com.github.qjerry.mapper.AdBusinessMapper;
import com.github.qjerry.mapper.AdBusinessSystemMapper;
import com.github.qjerry.vo.BusinessSystemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/3
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private AdBusinessMapper businessMapper;
    @Autowired
    private AdBusinessSystemMapper businessSystemMapper;

    @Override
    public BusinessSystemVO getBusinessSystem(Long businessId, Long systemId) throws CodeBoxApiException {
        AdBusiness adBusiness = businessMapper.selectById(businessId);
        if(Objects.isNull(adBusiness)) {
            throw new CodeBoxApiException("未正确匹配业务部门id，错误码接入失效！");
        }
        BusinessSystemVO vo = BusinessSystemVO.createBusinessSystem(adBusiness.getAdBusinessId(), adBusiness.getName(), null, null);

        if(Objects.nonNull(systemId)) {
            AdBusinessSystem adBusinessSystem = businessSystemMapper.selectById(systemId);
            Optional.ofNullable(adBusinessSystem).ifPresent(sys -> {
                vo.setAdBusinessSystemId(sys.getAdBusinessSystemId());
                vo.setSystemName(sys.getName());
                vo.setSystemCode(sys.getSystemCode());
            });
        }
        return vo;
    }

    @Override
    public BusinessSystemVO getSystem(Long systemId) throws CodeBoxApiException {
        AdBusinessSystem adBusinessSystem = businessSystemMapper.selectById(systemId);
        if(Objects.isNull(adBusinessSystem)) {
            throw new CodeBoxApiException("未正确匹配系统id！");
        }
        BusinessSystemVO vo = BusinessSystemVO.createSystem(systemId, adBusinessSystem.getName(), adBusinessSystem.getSystemCode());
        return vo;
    }
}

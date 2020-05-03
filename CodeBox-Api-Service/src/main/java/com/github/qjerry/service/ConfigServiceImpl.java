package com.github.qjerry.service;

import com.github.qjerry.entity.AdBusinessConfig;
import com.github.qjerry.enums.ApplyEnums;
import com.github.qjerry.config.CodeBoxApiException;
import com.github.qjerry.mapper.AdBusinessConfigMapper;
import com.github.qjerry.utils.CodeQueryWrapper;
import com.github.qjerry.vo.BusinessConfigVO;
import com.github.qjerry.vo.BusinessSystemVO;
import com.github.qjerry.vo.BusinessCodeConfigVO;
import com.github.qjerry.vo.BusinessCodeConfigVO.ApplyCodeInterval;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/3
 */
@Slf4j
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private AdBusinessConfigMapper adBusinessConfigMapper;
    @Autowired
    private BusinessService businessService;

    @Override
    public BusinessConfigVO getApplyConfig(Long businessId, Long systemId) throws CodeBoxApiException {
        businessService.getBusinessSystem(businessId, null);

        CodeQueryWrapper queryWrapper = CodeQueryWrapper.create().eq("ad_business_id", businessId);

        BusinessSystemVO system = businessService.getSystem(systemId);
        if(Objects.nonNull(system)) {
            queryWrapper.eq("ad_business_system_id", systemId);
        }

        // 获取业务部门+系统配置
        List<AdBusinessConfig> configs = adBusinessConfigMapper.selectList(queryWrapper);
        if(CollectionUtils.isEmpty(configs)) {
            return null;
        }

        BusinessConfigVO vo = new BusinessConfigVO();
        vo.setBusinessId(businessId);
        vo.setSystemId(systemId);
        vo.setConfigs(configs.stream().map(c -> BusinessConfigVO.ConfigVO.config(c.getHttpPort(), c.getErrCodeStartInterval(), c.getErrCodeEndInterval(), c.getStatus(), c.getFailureReason())).collect(Collectors.toList()));

        return vo;
    }

    @Override
    public BusinessCodeConfigVO getApplyBizcodeRange(Long businessId, Long systemId) throws CodeBoxApiException {
        BusinessConfigVO applyConfig = getApplyConfig(businessId, systemId);
        if(Objects.isNull(applyConfig)) {
            throw new CodeBoxApiException("业务系统暂未接入错误码平台！");
        }
        List<BusinessConfigVO.ConfigVO> configs = applyConfig.getConfigs().stream().filter(config -> config.getStatus() == ApplyEnums.SUCCESS.getCode()).collect(Collectors.toList());

        if(configs.size() == 0) {
            throw new CodeBoxApiException("业务系统申请配置暂未通过或者被拒绝！");
        }

        BusinessCodeConfigVO vo = BusinessCodeConfigVO.create(businessId, systemId);
        vo.setApplyCodeIntervals(configs.stream().map(m -> ApplyCodeInterval.create(m.getApplyCodeStartInterval(), m.getApplyCodeEndInterval())).collect(Collectors.toList()));
        vo.getApplyCodeIntervals().stream().forEach(applyCode -> log.info("最终得到错误码区间为[{}]-[{}]", applyCode.getApplyCodeStartInterval(), applyCode.getApplyCodeEndInterval()));
        return vo;
    }

}

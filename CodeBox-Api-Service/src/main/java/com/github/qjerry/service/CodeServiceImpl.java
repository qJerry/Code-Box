package com.github.qjerry.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.qjerry.dto.BusinessCodeDTO;
import com.github.qjerry.dto.BusinessSystemDTO;
import com.github.qjerry.dto.CodeDTO;
import com.github.qjerry.entity.AdBusinessCode;
import com.github.qjerry.entity.AdBusinessCodeHistory;
import com.github.qjerry.config.CodeBoxApiException;
import com.github.qjerry.mapper.AdBusinessCodeHistoryMapper;
import com.github.qjerry.mapper.AdBusinessCodeMapper;
import com.github.qjerry.utils.CodeQueryWrapper;
import com.github.qjerry.vo.BusinessCodeConfigVO;
import com.github.qjerry.vo.BusinessCodeConfigVO.ApplyCodeInterval;
import com.github.qjerry.vo.BusinessCodeVO;
import com.github.qjerry.vo.BusinessSystemVO;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/13
 */
@Service
@Slf4j
public class CodeServiceImpl implements CodeService {

    @Autowired
    private AdBusinessCodeMapper businessCodeMapper;
    @Autowired
    private AdBusinessCodeHistoryMapper businessCodeHistoryMapper;
    @Autowired
    private ConfigService configService;
    @Autowired
    private BusinessService businessService;

    public static void main(String[] args) {
        System.out.println(new Date());
    }

    @Override
    public int saveOrUpdateCode(BusinessCodeDTO businessCodeDTO) throws CodeBoxApiException {
        Date date = new Date();
        Long businessId = businessCodeDTO.getBusinessId();
        Long systemId = businessCodeDTO.getSystemId();
        log.debug("------------->> 开始批量校验并更新业务[{}] 系统[{}] 错误码 <<-------------", businessId, systemId);

        // 检测业务部门+系统，获取系统码
        BusinessSystemVO businessSystem = businessService.getBusinessSystem(businessId, systemId);
        Integer systemCode = businessSystem.getSystemCode() * 10000;

        BusinessCodeConfigVO applyBizcodeRange = configService.getApplyBizcodeRange(businessId, systemId);
        if(Objects.isNull(applyBizcodeRange)) {
            throw new CodeBoxApiException("未找到错误码申请记录");
        }
        List<CodeDTO> codeDTOs = businessCodeDTO.getCodes();
        if(codeDTOs.size() > 500) {
            throw new CodeBoxApiException("单次批量只允许最多500次");
        }
        // 过滤相同的错误码
        codeDTOs = codeDTOs.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<CodeDTO>(Comparator.comparing(CodeDTO::getCode))),
                ArrayList::new
        ));
        List<ApplyCodeInterval> applyCodeIntervals = applyBizcodeRange.getApplyCodeIntervals();
        applyCodeIntervals.stream().forEach(applyCodeInterval -> {
            UpdateWrapper wrapper = new UpdateWrapper<AdBusinessCode>();
            wrapper.set("status", 1);
            wrapper.gt("code", applyCodeInterval.getApplyCodeStartInterval());
            wrapper.lt("code", applyCodeInterval.getApplyCodeEndInterval());
//            wrapper.isNotNull("updated");
//            wrapper.lt("updated", date);
//            wrapper.or();
//            wrapper.isNull("updated");
//            wrapper.lt("created", date);
            businessCodeMapper.update(null, wrapper);
        });


        List<CodeDTO> finalCodeDTOs = Lists.newArrayList();
        for(CodeDTO codeDTO : codeDTOs) {
            Long code = Long.valueOf(systemCode) + codeDTO.getCode();

            boolean pass = false;
            for(ApplyCodeInterval applyCodeInterval : applyCodeIntervals) {
                Long start = applyCodeInterval.getApplyCodeStartInterval();
                Long end = applyCodeInterval.getApplyCodeEndInterval();
                if(code >= start && code <= end) {
                    pass = true;
                    finalCodeDTOs.add(codeDTO);
                    break;
                }
            }
            if(! pass) {
                throw new CodeBoxApiException("非法的错误码：" + codeDTO.getCode() + "，请检查错误码配置！");
            }
        }
        log.debug(">> 校验错误码成功，开始更新... <<");
        finalCodeDTOs.stream().forEach(codeDTO -> {
            Long code = Long.valueOf(systemCode) + codeDTO.getCode();
            AdBusinessCode businessCode = businessCodeMapper.selectOne(
                CodeQueryWrapper.create().one().eq("code", code)
            );
            if(Objects.isNull(businessCode)) {
                log.debug(">> 新增错误码：{} <<", code);
                businessCodeMapper.insert(new AdBusinessCode(code, codeDTO.getMessage(), codeDTO.getDetail(), systemId, date));
            } else {
                log.debug(">> 更新错误码：{} <<", code);
                businessCodeMapper.updateById(new AdBusinessCode(businessCode.getAdBusinessCodeId(), code, codeDTO.getMessage(), codeDTO.getDetail(), 0, systemId, date));
                // 备份到历史表
                businessCodeHistoryMapper.insert(new AdBusinessCodeHistory(businessCode));
            }
        });
        return 0;
    }

    @Override
    public List<BusinessCodeVO> getBusinessCode(BusinessSystemDTO businessSystemDTO) throws CodeBoxApiException {
        log.debug("------------->> 开始获取业务[{}] 系统[{}] 错误码 <<-------------", businessSystemDTO.getBusinessId(), businessSystemDTO.getSystemId());
        BusinessCodeConfigVO applyBizcodeRange = configService.getApplyBizcodeRange(businessSystemDTO.getBusinessId(), businessSystemDTO.getSystemId());
        if(Objects.isNull(applyBizcodeRange)) {
            throw new CodeBoxApiException("未找到错误码申请记录");
        }

        List<BusinessCodeVO> vos = Lists.newArrayList();
        applyBizcodeRange.getApplyCodeIntervals().stream().forEach(applyCode -> {
            Long start = applyCode.getApplyCodeStartInterval();
            Long end = applyCode.getApplyCodeEndInterval();
            List<AdBusinessCode> codes = businessCodeMapper.selectList(CodeQueryWrapper.create().eq("status", 0).between(false, "code", start, end));

            vos.addAll(codes.stream().map(m -> {
                String message = Strings.isNullOrEmpty(m.getOtherMessage()) ? m.getMessage() : m.getOtherMessage();
                return new BusinessCodeVO(m.getCode(), message, m.getDetail());
            }).collect(Collectors.toList()));
        });
        log.debug("获取错误码数量：{}", vos.size());
        return vos;
    }
}

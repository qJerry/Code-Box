package com.github.qjerry.service;

import com.github.qjerry.dto.BusinessCodeDTO;
import com.github.qjerry.dto.BusinessSystemDTO;
import com.github.qjerry.dto.LogDTO;
import com.github.qjerry.vo.CommonVO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/2
 */
@Slf4j
@Component
public class CodeBoxApiServiceFallbackFactory implements FallbackFactory<CodeBoxApiService> {

    @Override
    public CodeBoxApiService create(Throwable throwable) {
        if(Objects.nonNull(throwable) && ! StringUtils.isEmpty(throwable.getMessage())) {
            log.error("fallback reason : {}", throwable.getMessage());
        }
        return new CodeBoxApiService() {
            @Override
            public CommonVO getBusinessSystem(BusinessSystemDTO businessSystemDTO) {
                log.error("获取业务系统信息失败！");
                return CommonVO.err("获取业务系统信息失败！");
            }

            @Override
            public CommonVO getBusiness(BusinessSystemDTO businessSystemDTO) {
                log.error("获取业务信息失败！");
                return CommonVO.err("获取业务信息失败！");
            }

            @Override
            public CommonVO getSystem(BusinessSystemDTO businessSystemDTO) {
                log.error("获取系统信息失败！");
                return CommonVO.err("获取系统信息失败！");
            }

            @Override
            public CommonVO getConfig(BusinessSystemDTO businessSystemDTO) {
                log.error("获取配置信息失败！");
                return CommonVO.err("获取配置信息失败！");
            }

            @Override
            public CommonVO getApplyBizcodeRange(BusinessSystemDTO businessSystemDTO) {
                log.error("获取错误码区间信息失败！");
                return CommonVO.err("获取错误码区间信息失败！");
            }

            @Override
            public CommonVO batchSaveOrUpdate(BusinessCodeDTO businessCodeDTO) {
                log.error("批量保存错误码失败！");
                return CommonVO.err("批量保存错误码失败！");
            }

            @Override
            public CommonVO getBusinessCode(BusinessSystemDTO businessSystemDTO) {
                log.error("获取错误码信息失败！");
                return CommonVO.err("获取错误码信息失败！");
            }

            @Override
            public CommonVO addLog(LogDTO logDTO) {
                log.error("同步错误码日志失败！");
                return CommonVO.err("同步错误码日志失败！");
            }
        };
    }
}

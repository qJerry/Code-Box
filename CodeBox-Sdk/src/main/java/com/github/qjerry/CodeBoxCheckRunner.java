package com.github.qjerry;

import com.alibaba.fastjson.JSON;
import com.github.qjerry.common.StatusCode;
import com.github.qjerry.config.CodeBoxConfig;
import com.github.qjerry.dto.BusinessCodeDTO;
import com.github.qjerry.dto.BusinessSystemDTO;
import com.github.qjerry.dto.CodeDTO;
import com.github.qjerry.dto.StatusCodeDTO;
import com.github.qjerry.service.CodeBoxApiService;
import com.github.qjerry.utils.CodeUtils;
import com.github.qjerry.vo.BusinessCodeVO;
import com.github.qjerry.vo.BusinessSystemVO;
import com.github.qjerry.vo.CommonVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/3/29
 */
@Slf4j
@Component
public class CodeBoxCheckRunner implements ApplicationRunner {

    @Autowired
    private CodeBoxApiService bizCodeApiService;

    @Value("classpath:code.json")
    private Resource resource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Long businessId = CodeBoxConfig.businessId;
        Long businessSystemId = CodeBoxConfig.businessSystemId;
        log.info("------------->> 初始化检测错误码内容 <<-------------");
        log.debug("配置业务部门id：{}，配置业务系统id：{}", businessId, businessSystemId);

        if(Objects.isNull(businessId) || Objects.isNull(businessSystemId)) {
            return;
        }

        CommonVO<BusinessSystemVO> business = bizCodeApiService.getBusinessSystem(BusinessSystemDTO.create(businessId, businessSystemId));
        if(business.getCode() != StatusCode.STATUS_SUCCESS) {
            throw new RuntimeException("未正确匹配业务部门id，错误码接入失效！");
        }
        BusinessSystemVO businessSystemVO = business.getData();
        Long adBusinessSystemId = businessSystemVO.getAdBusinessSystemId();
        if(Objects.isNull(adBusinessSystemId)) {
            log.warn("未正确匹配业务系统id，准备接入业务部门的错误码！");
        } else {
            log.info("准备接入业务系统的错误码！");
        }

        log.info("------------->> 开始校验本地错误码内容 <<-------------");
        String areaData;
        try {
            areaData = IOUtils.toString(resource.getInputStream(), Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException("未找到错误码配置文件，请确认是否按照文档提示进行配置！");
        }
        List<StatusCodeDTO> statusCodes = JSON.parseArray(areaData, StatusCodeDTO.class);

        int size = statusCodes.stream().map(m -> m.getCode()).collect(Collectors.toSet()).size();
        if(size < statusCodes.size()) {
            throw new RuntimeException("检测到有重复的错误码，请处理！");
        }
        log.info("------------->> 开始同步本地错误码内容到错误码平台 <<-------------");
        List<CodeDTO> codes = statusCodes.stream().map(m -> CodeDTO.create(m.getCode(), m.getMessage(), m.getDetail())).collect(Collectors.toList());
        BusinessCodeDTO businessCodeDTO = new BusinessCodeDTO();
        businessCodeDTO.setCodes(codes);
        businessCodeDTO.setBusinessId(businessId);
        businessCodeDTO.setSystemId(adBusinessSystemId);
        CommonVO batchResult = bizCodeApiService.batchSaveOrUpdate(businessCodeDTO);
        if(batchResult.getCode() != StatusCode.STATUS_SUCCESS) {
            throw new RuntimeException("批量更新错误码失败！");
        }
        CommonVO<List<BusinessCodeVO>> businessCodes = bizCodeApiService.getBusinessCode(BusinessSystemDTO.create(businessId, businessSystemId));
        if(businessCodes.getCode() != StatusCode.STATUS_SUCCESS) {
            throw new RuntimeException("获取更新后的错误码失败！");
        }

        List<BusinessCodeVO> result = businessCodes.getData();
        CodeUtils.dynamicAddCode(result);
        log.info("------------->> 同步成功，同步数量：{} <<-------------", result.size());
    }
}

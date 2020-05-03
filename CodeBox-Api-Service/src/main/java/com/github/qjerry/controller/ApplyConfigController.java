package com.github.qjerry.controller;

import com.github.qjerry.dto.BusinessSystemDTO;
import com.github.qjerry.config.CodeBoxApiException;
import com.github.qjerry.service.ConfigService;
import com.github.qjerry.vo.CommonVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: 业务/系统申请的配置</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/3
 */
@Slf4j
@RestController
@RequestMapping("/apply/config")
public class ApplyConfigController extends BaseController {

    @Autowired
    private ConfigService configService;

    @PostMapping("/get")
    public CommonVO getConfig(@RequestBody BusinessSystemDTO businessSystemDTO) throws CodeBoxApiException {
        if(Objects.isNull(businessSystemDTO.getBusinessId())) {
            return CommonVO.err("业务参数有误！");
        }
        return success(configService.getApplyConfig(businessSystemDTO.getBusinessId(), businessSystemDTO.getSystemId()));
    }

    @PostMapping("/getApplyBizcodeRange")
    public CommonVO getApplyBizcodeRange(@RequestBody BusinessSystemDTO businessSystemDTO) throws CodeBoxApiException {
        if(Objects.isNull(businessSystemDTO.getBusinessId())) {
            return CommonVO.err("业务参数有误！");
        }
        return success(configService.getApplyBizcodeRange(businessSystemDTO.getBusinessId(), businessSystemDTO.getSystemId()));
    }
}

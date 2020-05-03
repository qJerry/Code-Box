package com.github.qjerry.controller;

import com.github.qjerry.dto.BusinessSystemDTO;
import com.github.qjerry.config.CodeBoxApiException;
import com.github.qjerry.service.BusinessService;
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
 * <p>Desc: 业务、系统</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/2
 */
@Slf4j
@RestController
@RequestMapping("/business")
public class BusinessController extends BaseController {

    @Autowired
    private BusinessService businessService;

    @PostMapping("/getBusinessSystem")
    public CommonVO getBusinessSystem(@RequestBody BusinessSystemDTO businessSystemDTO) throws CodeBoxApiException {
        if(Objects.isNull(businessSystemDTO.getBusinessId())) {
            return CommonVO.err("业务参数有误！");
        }
        return success(businessService.getBusinessSystem(businessSystemDTO.getBusinessId(), businessSystemDTO.getSystemId()));
    }

    @PostMapping("/getBusiness")
    public CommonVO getBusiness(@RequestBody BusinessSystemDTO businessSystemDTO) throws CodeBoxApiException {
        if(Objects.isNull(businessSystemDTO.getBusinessId())) {
            return CommonVO.err("业务参数有误！");
        }
        return success(businessService.getBusinessSystem(businessSystemDTO.getBusinessId(), null));
    }

    @PostMapping("/getSystem")
    public CommonVO getSystem(@RequestBody BusinessSystemDTO businessSystemDTO) throws CodeBoxApiException {
        if(Objects.isNull(businessSystemDTO.getSystemId())) {
            return CommonVO.err("系统参数有误！");
        }
        return success(businessService.getSystem(businessSystemDTO.getSystemId()));
    }
}

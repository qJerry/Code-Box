package com.github.qjerry.controller;

import com.github.qjerry.dto.BusinessCodeDTO;
import com.github.qjerry.dto.BusinessSystemDTO;
import com.github.qjerry.config.CodeBoxApiException;
import com.github.qjerry.service.CodeService;
import com.github.qjerry.vo.CommonVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: 错误码</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/13
 */
@Slf4j
@RestController
@RequestMapping("/code")
public class CodeController extends BaseController {

    @Autowired
    private CodeService codeService;

    /**
     * 批量添加或更改错误码
     * @param businessCodeDTO
     * @return
     * @throws CodeBoxApiException
     */
    @RequestMapping("/batchSaveOrUpdate")
    public CommonVO batchSaveOrUpdate(@RequestBody BusinessCodeDTO businessCodeDTO) throws CodeBoxApiException {
        return success(codeService.saveOrUpdateCode(businessCodeDTO));
    }

    /**
     * 指定业务部门获取最新错误码
     * @param businessSystemDTO
     * @return
     * @throws CodeBoxApiException
     */
    @RequestMapping("/getBusinessCode")
    public CommonVO getBusinessCode(@RequestBody BusinessSystemDTO businessSystemDTO) throws CodeBoxApiException {
        return success(codeService.getBusinessCode(businessSystemDTO));
    }
}

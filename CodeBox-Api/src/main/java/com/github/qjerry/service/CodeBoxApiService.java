package com.github.qjerry.service;

import com.github.qjerry.dto.BusinessCodeDTO;
import com.github.qjerry.dto.BusinessSystemDTO;
import com.github.qjerry.dto.LogDTO;
import com.github.qjerry.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/2
 */
@FeignClient(name = "codebox-service", fallbackFactory = CodeBoxApiServiceFallbackFactory.class)
public interface CodeBoxApiService {

    /**
     * 获取业务系统信息
     * @param businessSystemDTO
     * @return
     */
    @PostMapping("/u/a/business/getBusinessSystem")
    CommonVO<BusinessSystemVO> getBusinessSystem(@RequestBody BusinessSystemDTO businessSystemDTO);

    /**
     * 获取业务信息
     * @param businessSystemDTO
     * @return
     */
    @PostMapping("/u/a/business/getBusiness")
    CommonVO<BusinessSystemVO> getBusiness(@RequestBody BusinessSystemDTO businessSystemDTO);

    /**
     * 获取业务系统信息
     * @param businessSystemDTO
     * @return
     */
    @PostMapping("/u/a/business/systemId")
    CommonVO<BusinessSystemVO> getSystem(@RequestBody BusinessSystemDTO businessSystemDTO);

    /**
     * 获取申请配置
     * @param businessSystemDTO
     * @return
     */
    @PostMapping("/u/a/apply/config/get")
    CommonVO<BusinessConfigVO> getConfig(@RequestBody BusinessSystemDTO businessSystemDTO);

    /**
     * 获取申请的错误码
     * @param businessSystemDTO
     * @return
     */
    @PostMapping("/u/a/apply/config/getApplyBizcodeRange")
    CommonVO<BusinessCodeConfigVO> getApplyBizcodeRange(@RequestBody BusinessSystemDTO businessSystemDTO);

    /**
     * 批量添加或更改错误码
     * @param businessCodeDTO
     * @return
     */
    @PostMapping("/u/a/code/batchSaveOrUpdate")
    CommonVO batchSaveOrUpdate(@RequestBody BusinessCodeDTO businessCodeDTO);

    /**
     * 指定业务部门获取最新错误码
     * @param businessSystemDTO
     * @return
     */
    @PostMapping("/u/a/code/getBusinessCode")
    CommonVO<List<BusinessCodeVO>> getBusinessCode(@RequestBody BusinessSystemDTO businessSystemDTO);

    /**
     * 同步错误日志
     * @param logDTO
     * @return
     */
    @PostMapping("/u/a/log/sync")
    CommonVO addLog(@RequestBody LogDTO logDTO);
}

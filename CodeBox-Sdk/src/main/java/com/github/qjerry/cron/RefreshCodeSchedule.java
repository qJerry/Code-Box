package com.github.qjerry.cron;

import com.github.qjerry.common.StatusCode;
import com.github.qjerry.config.CodeBoxConfig;
import com.github.qjerry.dto.BusinessSystemDTO;
import com.github.qjerry.service.CodeBoxApiService;
import com.github.qjerry.utils.CodeUtils;
import com.github.qjerry.vo.BusinessCodeVO;
import com.github.qjerry.vo.CommonVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: 周期性刷新错误码</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/30
 */
@Slf4j
@Component
@EnableScheduling
@ConditionalOnProperty(name = "code.refresh.open", havingValue = "true")
public class RefreshCodeSchedule {

    @Autowired
    private CodeBoxApiService bizCodeApiService;

    /**
     * 刷新错误码
     */
    @Scheduled(cron = "${code.refresh.cron}")
    public void refresh() {
        log.debug("\n==================== 开始刷新错误码 ====================");
        CommonVO<List<BusinessCodeVO>> businessCodes = bizCodeApiService.getBusinessCode(BusinessSystemDTO.create(CodeBoxConfig.businessId, CodeBoxConfig.businessSystemId));
        if(businessCodes.getCode() != StatusCode.STATUS_SUCCESS) {
            log.error("获取更新后的错误码失败！");
            return;
        }
        List<BusinessCodeVO> datas = businessCodes.getData();
        if(CollectionUtils.isEmpty(datas)) {
            log.error("获取的错误码信息为空！");
            return;
        }
        try {
            CodeUtils.dynamicAddCode(businessCodes.getData());
        } catch (Exception e) {
            log.error("同步错误码失败！\n {}", e.getMessage());
            return;
        }
        log.debug("\n==================== 刷新错误码成功 ====================");
    }

}

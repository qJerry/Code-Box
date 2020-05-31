package com.github.qjerry.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>Title:API-ERRCODE</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/5/13
 */
@Data
@Component
public class CodeBoxConfig {

    public static Long businessId;

    public static Long businessSystemId;

    public static boolean syncLog;

    @Value("${code.business.id:1}")
    public void setBusinessId(Long businessId) {
        CodeBoxConfig.businessId = businessId;
    }

    @Value("${code.business.system.id:1}")
    private void setBusinessSystemId(Long businessSystemId) {
        CodeBoxConfig.businessSystemId = businessSystemId;
    }

    @Value("${code.sync-log:false}")
    private void setSyncLog(boolean syncLog) {
        CodeBoxConfig.syncLog = syncLog;
    }

}

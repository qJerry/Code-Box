package com.github.qjerry.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: 业务配置出参</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/3
 */
@Data
public class BusinessConfigVO implements Serializable {

    private static final long serialVersionUID = 4471836789349889620L;

    private Long businessId;

    private Long systemId;

    private List<ConfigVO> configs;

    @Data
    public static class ConfigVO {
        /**
         * 申请端口
         */
        private String applyPort;

        /**
         * 错误码起始区间
         */
        private Long applyCodeStartInterval;

        /**
         * 错误码结束区间
         */
        private Long applyCodeEndInterval;

        /**
         * 状态，0：通过，1：不通过
         */
        private Integer status;

        /**
         * 不通过原因
         */
        private String failureReason;

        public ConfigVO() {
        }

        public static ConfigVO config(String applyPort, Long applyCodeStartInterval, Long applyCodeEndInterval, Integer status, String failureReason) {
            ConfigVO config = new ConfigVO();
            config.setApplyPort(applyPort);
            config.setApplyCodeStartInterval(applyCodeStartInterval);
            config.setApplyCodeEndInterval(applyCodeEndInterval);
            config.setStatus(status);
            config.setFailureReason(failureReason);
            return config;
        }
    }


}

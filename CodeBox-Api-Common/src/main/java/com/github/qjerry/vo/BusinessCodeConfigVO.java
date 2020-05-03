package com.github.qjerry.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: 错误码区间配置出参</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/3
 */
@Data
public class BusinessCodeConfigVO implements Serializable {

    private static final long serialVersionUID = 6426575264267264776L;

    private Long businessId;

    private Long systemId;

    private List<ApplyCodeInterval> applyCodeIntervals;

    @Data
    public static class ApplyCodeInterval {
        /**
         * 错误码起始区间
         */
        private Long applyCodeStartInterval;

        /**
         * 错误码结束区间
         */
        private Long applyCodeEndInterval;

        public static ApplyCodeInterval create(Long applyCodeStartInterval, Long applyCodeEndInterval) {
            ApplyCodeInterval applyCodeInterval = new ApplyCodeInterval();
            applyCodeInterval.setApplyCodeStartInterval(applyCodeStartInterval);
            applyCodeInterval.setApplyCodeEndInterval(applyCodeEndInterval);
            return applyCodeInterval;
        }
    }


    public BusinessCodeConfigVO() {
    }

    public static BusinessCodeConfigVO create(Long businessId, Long systemId) {
        BusinessCodeConfigVO vo = new BusinessCodeConfigVO();
        vo.setBusinessId(businessId);
        vo.setSystemId(systemId);
        return vo;
    }
}

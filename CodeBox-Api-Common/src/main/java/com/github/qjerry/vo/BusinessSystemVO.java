package com.github.qjerry.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: 业务、系统出参</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/3
 */
@Data
public class BusinessSystemVO implements Serializable {

    private static final long serialVersionUID = -836310290100241805L;

    /**
     * 业务id
     */
    private Long adBusinessId;

    /**
     * 业务名称
     */
    private String businessName;

    /**
     * 系统id
     */
    private Long adBusinessSystemId;

    /**
     * 系统名称
     */
    private String systemName;

    private Integer systemCode;

    public BusinessSystemVO() {
    }

    public static BusinessSystemVO createBusinessSystem(Long adBusinessId, String businessName, Long adBusinessSystemId, String systemName) {
        BusinessSystemVO vo = new BusinessSystemVO();
        vo.setAdBusinessId(adBusinessId);
        vo.setBusinessName(businessName);
        vo.setAdBusinessSystemId(adBusinessSystemId);
        vo.setSystemName(systemName);
        return vo;
    }

    public static BusinessSystemVO createSystem(Long adBusinessSystemId, String systemName, Integer systemCode) {
        BusinessSystemVO vo = new BusinessSystemVO();
        vo.setAdBusinessSystemId(adBusinessSystemId);
        vo.setSystemName(systemName);
        vo.setSystemCode(systemCode);
        return vo;
    }
}

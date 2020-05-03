package com.github.qjerry.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/11
 */
@Data
public class BusinessSystemDTO implements Serializable {

    private static final long serialVersionUID = -4622462146030746185L;

    /**
     * 业务部门id
     */
    private Long businessId;
    
    /**
     * 业务系统id
     */
    private Long systemId;

    public static BusinessSystemDTO create(Long businessId, Long systemId) {
        BusinessSystemDTO businessSystemDTO = new BusinessSystemDTO();
        businessSystemDTO.setBusinessId(businessId);
        businessSystemDTO.setSystemId(systemId);
        return businessSystemDTO;
    }

    public static BusinessSystemDTO busienss(Long businessId) {
        BusinessSystemDTO businessSystemDTO = new BusinessSystemDTO();
        businessSystemDTO.setBusinessId(businessId);
        return businessSystemDTO;
    }

    public static BusinessSystemDTO system(Long systemId) {
        BusinessSystemDTO businessSystemDTO = new BusinessSystemDTO();
        businessSystemDTO.setSystemId(systemId);
        return businessSystemDTO;
    }
}

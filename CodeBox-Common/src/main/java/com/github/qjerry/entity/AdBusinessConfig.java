package com.github.qjerry.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 业务部门系统配置申请表
 * </p>
 *
 * @author Jerry
 * @since 2020-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AdBusinessConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ad_business_config_id", type = IdType.AUTO)
    private Integer adBusinessConfigId;

    /**
     * 申请http端口
     */
    private String httpPort;

    /**
     * 申请域名
     */
    private String domain;

    /**
     * 错误码起始区间
     */
    private Long errCodeStartInterval;

    /**
     * 错误码结束区间
     */
    private Long errCodeEndInterval;

    /**
     * 业务部门id
     */
    private Integer adBusinessId;

    /**
     * 业务系统id
     */
    private Integer adBusinessSystemId;

    /**
     * 状态，0：通过，1：不通过
     */
    private Integer status;

    /**
     * 不通过原因
     */
    private String failureReason;

    private LocalDateTime created;

    private Integer createdby;


}

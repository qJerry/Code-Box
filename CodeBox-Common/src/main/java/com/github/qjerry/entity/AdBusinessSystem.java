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
 * 业务系统表
 * </p>
 *
 * @author Jerry
 * @since 2020-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AdBusinessSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ad_business_system_id", type = IdType.AUTO)
    private Long adBusinessSystemId;

    /**
     * 系统码，4位组成
     */
    private Integer systemCode;

    /**
     * 系统名称
     */
    private String name;

    /**
     * 系统描述
     */
    private String description;

    /**
     * 业务部门id
     */
    private Integer adBusinessId;

    /**
     * 是否可用
     */
    private String usable;

    private LocalDateTime created;

    private Integer createdby;


}

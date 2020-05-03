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
 * 业务部门表
 * </p>
 *
 * @author Jerry
 * @since 2020-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AdBusiness implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ad_business_id", type = IdType.AUTO)
    private Long adBusinessId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门负责人
     */
    private String principal;

    /**
     * 是否可用
     */
    private String usable;

    private LocalDateTime created;

    private Integer createdby;


}

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
 * 业务错误日志表
 * </p>
 *
 * @author Jerry
 * @since 2020-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AdBusinessCodeLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ad_business_error_log_id", type = IdType.AUTO)
    private Long adBusinessErrorLogId;

    /**
     * 业务部门id
     */
    private Long adBusinessId;

    /**
     * 业务系统id
     */
    private Long adBusinessSystemId;

    /**
     * 日志内容
     */
    private String content;

    private LocalDateTime created;

    private Long createdby;


}

package com.github.qjerry.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 错误码历史表
 * </p>
 *
 * @author Jerry
 * @since 2020-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AdBusinessCodeHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "ad_business_code_history_id", type = IdType.AUTO)
    private Long adBusinessCodeHistoryId;

    /**
     * 错误码id
     */
    private Long adBusinessCodeId;

    /**
     * 错误码
     */
    private Long code;

    /**
     * 错误消息
     */
    private String message;

    /**
     * 错误详细内容，包括解决方案等
     */
    private String detail;

    /**
     * 其他错误消息，例如产品在后台设置的错误描述
     */
    private String otherMessage;

    /**
     * 其他错误详细内容，例如产品在后台设置的错误内容
     */
    private String otherDetail;

    /**
     * 0：生效，1：无效
     */
    private Integer status;

    private Date created;

    private Long createdby;

    private Date updated;

    private Long updatedby;

    public AdBusinessCodeHistory(AdBusinessCode businessCode) {
        this.adBusinessCodeId = businessCode.getAdBusinessCodeId();
        this.code = businessCode.getCode();
        this.message = businessCode.getMessage();
        this.detail = businessCode.getDetail();
        this.otherMessage = businessCode.getOtherMessage();
        this.otherDetail = businessCode.getOtherDetail();
        this.status = businessCode.getStatus();
        this.created = businessCode.getCreated();
        this.createdby = businessCode.getCreatedby();
        this.updated = businessCode.getUpdated();
        this.updatedby = businessCode.getUpdatedby();
    }
}

package com.zp.sef.common.model.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 实体类基类
 *
 * @author ZP
 */
@Data
public abstract class BaseEntity {

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @Schema(description = "是否已删除")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;

    @Schema(description = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Schema(description = "创建用户id")
    @TableField(fill = FieldFill.INSERT)
    private String createUserId;

    @Schema(description = "更新用户id")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;

    @Schema(description = "创建用户ip")
    @TableField(fill = FieldFill.INSERT)
    private String createUserIp;

    @Schema(description = "更新用户ip")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUserIp;

}

package com.zp.sef.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.sef.common.model.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author mybatis-plus-generator-3.5.1
 */
@Data
@TableName("user_info")
@Schema(name = "UserInfo对象", description = "用户信息表")
public class UserInfo extends BaseEntity {

    @Schema(description = "用户名（唯一）")
    @TableField("username")
    private String username;

    @Schema(description = "密码")
    @TableField("password")
    private String password;

    @Schema(description = "电子邮件（唯一）")
    @TableField("email")
    private String email;

    @Schema(description = "用户状态（0: 禁用, 1: 启用）")
    @TableField("status")
    private Integer status;

}

package com.zp.sef.user.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
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
public class UserInfo {

    @Schema(description = "用户 ID（主键）")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "用户名（唯一）")
    @TableField("username")
    private String username;

    @Schema(description = "密码")
    @TableField("password")
    private String password;

    @Schema(description = "电子邮件（唯一）")
    @TableField("email")
    private String email;

    @Schema(description = "创建时间")
    @TableField("created_at")
    private Date createdAt;

    @Schema(description = "最后更新时间")
    @TableField("updated_at")
    private Date updatedAt;

    @Schema(description = "用户状态（0: 禁用, 1: 启用）")
    @TableField("status")
    private Integer status;


}

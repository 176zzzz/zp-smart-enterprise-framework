/*
 * chsi
 * Created on 2024-10-18
 */
package com.zp.sef.auth.vo;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author zp
 */
@Data
public class LoginVo {

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String password;

}

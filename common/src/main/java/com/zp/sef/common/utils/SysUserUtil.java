package com.zp.sef.common.utils;

import com.zp.sef.common.model.user.LoginUser;

/**
 * SysUserUtil
 *
 * @author ZP
 */
public class SysUserUtil {

    /**
     * 获取当前登录用户信息
     *
     * @return LoginUser
     */
    public static LoginUser getLoginUserInfo() {
        // TODO 补充用户相关逻辑
        LoginUser loginUser = new LoginUser();
        loginUser.setId("11111");
        return loginUser;
    }

    /**
     * 获取当前登录用户id
     *
     * @return LoginUserId
     */
    public static String getLoginUserId() {
        // TODO 补充用户相关逻辑
        return "11111";
    }

    /**
     * 获取当前登录用户ip
     *
     * @return LoginUserIp
     */
    public static String getLoginUserIp() {
        // TODO 补充用户相关逻辑
        return "127.0.0.1";
    }

}

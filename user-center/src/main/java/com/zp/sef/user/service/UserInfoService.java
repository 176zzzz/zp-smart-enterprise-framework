package com.zp.sef.user.service;

import com.zp.sef.common.model.user.LoginUser;
import com.zp.sef.user.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author mybatis-plus-generator-3.5.1
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * findByUsername
     *
     * @param userName String
     * @return UserInfo
     */
    UserInfo findByUsername(String userName);

    /**
     * findLoginUserByUsername
     *
     * @param userName String
     * @return LoginUser
     */
    LoginUser findLoginUserByUsername(String userName);

    /**
     * addUser
     * @param userInfo UserInfo
     * @return boolean
     */
    boolean add(UserInfo userInfo);


}

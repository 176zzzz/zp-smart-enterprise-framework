package com.zp.sef.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.sef.common.model.user.LoginUser;
import com.zp.sef.user.entity.UserInfo;
import com.zp.sef.user.mapper.UserInfoMapper;
import com.zp.sef.user.service.UserInfoService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author mybatis-plus-generator-3.5.1
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserInfo findByUsername(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return null;
        }
        List<UserInfo> list = this.list(Wrappers.<UserInfo>lambdaQuery()
                .eq(UserInfo::getUsername, userName));
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    @Override
    public LoginUser findLoginUserByUsername(String userName) {
        LoginUser loginUser = null;
        UserInfo userInfo = findByUsername(userName);
        if (userInfo != null) {
            loginUser = new LoginUser();
            loginUser.setId(userInfo.getId());
            loginUser.setUserName(userName);
            loginUser.setPassword(userInfo.getPassword());
            loginUser.setEnabled(userInfo.isEnabled());
        }
        //TODO 增加权限

        return loginUser;
    }

    @Override
    public boolean add(UserInfo userInfo) {
        //密码加密
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return this.save(userInfo);
    }

}

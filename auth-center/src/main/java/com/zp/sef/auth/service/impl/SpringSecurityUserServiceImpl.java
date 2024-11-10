/*
 * chsi
 * Created on 2024-10-21
 */
package com.zp.sef.auth.service.impl;

import com.zp.sef.auth.feign.UserFeignClient;
import com.zp.sef.common.model.user.LoginUser;
import com.zp.sef.common.model.web.ResponseResult;
import javax.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author zp
 * @version $Id$
 */
@Service
public class SpringSecurityUserServiceImpl implements UserDetailsService {

    @Resource
    private UserFeignClient userFeignClient;

    @Override
    public LoginUser loadUserByUsername(String username) throws UsernameNotFoundException {
        ResponseResult<LoginUser> result = userFeignClient.findByUsername(username);
        if (result == null || result.getData() == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return result.getData();
    }

}

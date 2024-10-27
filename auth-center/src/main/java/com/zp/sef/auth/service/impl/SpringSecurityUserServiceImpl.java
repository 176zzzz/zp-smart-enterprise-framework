/*
 * chsi
 * Created on 2024-10-21
 */
package com.zp.sef.auth.service.impl;

import com.zp.sef.auth.feign.UserFeignClient;
import com.zp.sef.common.model.user.LoginUser;
import com.zp.sef.common.model.web.ResponseResult;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResponseResult<LoginUser> loginUser = userFeignClient.findByUsername(username);
        if (loginUser == null || loginUser.getData() == null) {
            throw new UsernameNotFoundException("user not found");
        }

        //TODO 用户权限
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        GrantedAuthority grantedAuthority = () -> null;
        grantedAuthorityList.add(grantedAuthority);

        return new User(username, loginUser.getData().getPassword(), grantedAuthorityList);
    }

}

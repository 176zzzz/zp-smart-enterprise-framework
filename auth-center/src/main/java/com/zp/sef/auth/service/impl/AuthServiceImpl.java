/*
 * chsi
 * Created on 2024-10-18
 */
package com.zp.sef.auth.service.impl;

import com.zp.sef.auth.service.AuthService;
import com.zp.sef.common.auth.storage.TokenStorage;
import com.zp.sef.auth.vo.LoginVo;
import com.zp.sef.common.auth.authentication.JwtUsernamePasswordAuthenticationToken;
import com.zp.sef.common.model.user.LoginUser;
import com.zp.sef.common.utils.JwtTokenUtil;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author zp
 * @version $Id$
 */

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private TokenStorage tokenStorage;

    @Override
    public String login(LoginVo loginVo) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //1.根据用户名获取用户信息
        UserDetails userDetails = new LoginUser(loginVo.getUserName(), loginVo.getPassword());
        //2.密码解密，验证
        JwtUsernamePasswordAuthenticationToken authentication = new JwtUsernamePasswordAuthenticationToken(userDetails,
                userDetails.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        //3.更新authentication
        LoginUser principal = (LoginUser) authenticate.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = JwtTokenUtil.createToken(authenticate);
        tokenStorage.store(principal.getId(), token);
        return token;
    }
}

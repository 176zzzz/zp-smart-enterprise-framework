package com.zp.sef.common.auth.authentication;

import com.zp.sef.common.model.user.LoginUser;
import javax.annotation.Resource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * JwtAuthenticationProvider
 *
 * @author ZP
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserDetailsService springSecurityUserServiceImpl;

    @Resource
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //用户登录用户
        LoginUser principal = (LoginUser) authentication.getPrincipal();
        if (principal == null) {
            return authentication;
        }
        //数据库中加载用户
        UserDetails userDetails = springSecurityUserServiceImpl.loadUserByUsername(principal.getUsername());
        if (userDetails == null) {
            throw new UsernameNotFoundException("user not found");
        }
        //比较两个用户是否相等
        if (passwordEncoder.matches(principal.getPassword(), userDetails.getPassword())) {
            return new JwtUsernamePasswordAuthenticationToken(userDetails.getAuthorities(), userDetails,
                    userDetails.getPassword());
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtUsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}

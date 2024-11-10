/*
 * chsi
 * Created on 2024-10-21
 */
package com.zp.sef.common.config;

import com.zp.sef.common.auth.authentication.JwtAuthenticationEntryPoint;
import com.zp.sef.common.auth.storage.TokenStorage;
import com.zp.sef.common.auth.authentication.JwtAuthenticationProvider;
import com.zp.sef.common.auth.authentication.filter.JwtAuthenticationfilter;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;

/**
 * springSecurity全局配置
 *
 * @author zp
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private TokenStorage tokenStorage;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //配置需要授权的接口路径
                .authorizeHttpRequests(auth -> auth
                        //白名单
                        .antMatchers("/userInfo/findByUsername", "/auth/login").permitAll()
                        //其余所有接口都需要认证
                        .anyRequest().authenticated()
                )

                //增加自定义过滤器
                .addFilterAfter(new JwtAuthenticationfilter(tokenStorage, "/userInfo/findByUsername", "/auth/login"),
                        ExceptionTranslationFilter.class)
                //关闭匿名访问
//                .anonymous().disable()
                .formLogin().disable()
                .logout().disable()
                //远程api访问配置，即如果此项开启通过api访问会弹出登录框，不开启会返回登录页面
                .httpBasic().disable()
                //对于所有非get方法，默认开启csrf保护，都必须在header中加上csrftoken，就算配置了白名单也没用
                .csrf().disable()
                //request失败后缓存
                .requestCache().disable()
                //记住我，生成一个包含用户身份信息的cookie，并在用户浏览器上保存，并且默认使用一个密匙来加密
                .rememberMe().disable();

        // 添加JwtAuthenticationEntryPoint来处理未认证的请求
        http.exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return null;
            }
        };
    }


    @Bean
    public AuthenticationManager authenticationManagerBean(JwtAuthenticationProvider authenticationProvider) {
        return new ProviderManager(authenticationProvider);
    }


}

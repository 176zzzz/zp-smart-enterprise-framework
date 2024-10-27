/*
 * chsi
 * Created on 2024-10-21
 */
package com.zp.sef.common.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * springSecurity全局配置
 *
 * @author zp
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //配置需要认证的接口路径
                .authorizeHttpRequests(auth -> auth
                        //白名单
                        .antMatchers("/userInfo/findByUsername").permitAll()
                        //其余所有接口都需要认证
                        .anyRequest().authenticated()
                )
//                .formLogin( form -> form
//                        .loginPage("/login") // 自定义登录页面
//                        .loginProcessingUrl("/login") // 处理登录请求的URL
//                        .defaultSuccessUrl("/welcome", true) // 登录成功后的默认跳转URL
//                        .failureUrl("/login?error") // 登录失败后的URL
//                        .permitAll() // 允许任何人访问登录页面
//                )
                .formLogin(withDefaults())
                .logout(withDefaults())
                //远程api访问配置，即如果此项开启通过api访问会弹出登录框，不开启会返回登录页面
                .httpBasic(withDefaults())
                //对于所有非get方法，默认开启csrf保护，都必须在header中加上csrftoken，就算配置了白名单也没用
                .csrf(withDefaults())
                //记住我，生成一个包含用户身份信息的cookie，并在用户浏览器上保存，并且默认使用一个密匙来加密
                .rememberMe(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

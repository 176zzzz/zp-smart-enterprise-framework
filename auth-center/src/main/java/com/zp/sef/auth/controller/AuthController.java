/*
 * chsi
 * Created on 2024-10-15
 */
package com.zp.sef.auth.controller;

import com.zp.sef.auth.service.AuthService;
import com.zp.sef.auth.vo.LoginVo;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zp
 * @version $Id$
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AuthService authService;


    @PostMapping("/auth")
    public void login(@RequestBody LoginVo loginVo) {
        authService.login(loginVo);
    }


}

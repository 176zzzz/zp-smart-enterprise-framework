/*
 * chsi
 * Created on 2024-10-15
 */
package com.zp.sef.auth.controller;

import com.zp.sef.auth.service.AuthService;
import com.zp.sef.auth.vo.LoginVo;
import com.zp.sef.common.model.web.ResponseResult;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/test")
    public String test() {
        return "222";
    }

    /**
     * login
     *
     * @param loginVo LoginVo
     * @param response HttpServletResponse
     * @param request HttpServletRequest
     * @return ResponseResult<String>
     */
    @PostMapping("/login")
    public ResponseResult<String> login(@RequestBody LoginVo loginVo, HttpServletResponse response,
            HttpServletRequest request) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return ResponseResult.success(authService.login(loginVo));
    }


}

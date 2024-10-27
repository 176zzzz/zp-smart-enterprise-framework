/*
 * chsi
 * Created on 2024-10-18
 */
package com.zp.sef.auth.service.impl;

import com.zp.sef.auth.service.AuthService;
import com.zp.sef.auth.vo.LoginVo;
import com.zp.sef.common.model.web.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * @author zp
 * @version $Id$
 */

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public ResponseResult<String> login(LoginVo loginVo) {
        //1.根据用户名获取用户信息

        //2.密码解密，验证

        //3.生成token,存入redis中

        return null;
    }
}

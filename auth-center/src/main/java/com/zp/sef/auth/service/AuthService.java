/*
 * chsi
 * Created on 2024-10-18
 */
package com.zp.sef.auth.service;

import com.zp.sef.auth.vo.LoginVo;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;

/**
 * @author zp
 * @version $Id$
 */
@Validated
public interface AuthService {

    /**
     * 登录，返回用户token
     *
     * @param loginVo LoginVo
     * @return ResponseResult<String>
     */
    String login(@Valid LoginVo loginVo) throws InvalidKeySpecException, NoSuchAlgorithmException;

}

/*
 * chsi
 * Created on 2024-10-18
 */
package com.zp.sef.auth.service;

import com.zp.sef.auth.vo.LoginVo;
import com.zp.sef.common.model.web.ResponseResult;
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
    ResponseResult<String> login(@Valid LoginVo loginVo);

}

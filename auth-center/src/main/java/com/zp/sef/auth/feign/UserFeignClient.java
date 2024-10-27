package com.zp.sef.auth.feign;

import com.zp.sef.auth.feign.impl.UserFeignClientFallbackFactory;
import com.zp.sef.common.model.user.LoginUser;
import com.zp.sef.common.model.web.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * UserFeignClient
 *
 * @author ZP
 */
@FeignClient(value = "user-center", fallbackFactory = UserFeignClientFallbackFactory.class, decode404 = true)
public interface UserFeignClient {


    /**
     * findByUsername
     *
     * @param username String
     * @return LoginUser
     */
    @GetMapping("/userInfo/findByUsername")
    public ResponseResult<LoginUser> findByUsername(@RequestParam("username") String username);
}

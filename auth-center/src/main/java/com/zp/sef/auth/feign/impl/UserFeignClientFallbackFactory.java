package com.zp.sef.auth.feign.impl;

import com.zp.sef.auth.feign.UserFeignClient;
import com.zp.sef.common.model.web.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * UserFeignClientFallbackFactory
 *
 * @author ZP
 */
@Slf4j
@Component
public class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {

    @Override
    public UserFeignClient create(Throwable throwable) {
        return username -> {
            log.error("根据用户名查询用户异常", throwable);
            return ResponseResult.fail("根据用户名查询用户异常" + throwable.getMessage());
        };
    }
}

package com.zp.sef.common.config;

import feign.Logger.Level;
import feign.Request;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;

/**
 * feign配置
 *
 * @author ZP
 */
public class GlobalFeignConfig {

    /**
     * 日志处理
     *
     * @return
     */
    @Bean
    public Level level() {
        return Level.FULL;
    }

    /**
     * 全局超时配置
     *
     * @return
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, TimeUnit.MILLISECONDS, 10000, TimeUnit.MILLISECONDS, true);
    }
}

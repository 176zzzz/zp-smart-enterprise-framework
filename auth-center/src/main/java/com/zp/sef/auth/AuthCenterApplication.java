package com.zp.sef.auth;

import com.zp.sef.common.config.GlobalFeignConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.zp.sef.common.**", "com.zp.sef.auth.**"})
@EnableFeignClients(defaultConfiguration = GlobalFeignConfig.class)
public class AuthCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthCenterApplication.class, args);
    }

}

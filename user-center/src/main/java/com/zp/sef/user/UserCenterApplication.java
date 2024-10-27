package com.zp.sef.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * UserCenterApplication
 *
 * @author ZP
 */
@SpringBootApplication(scanBasePackages = {"com.zp.sef.**", "com.zp.sef.common.**"})
public class UserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class, args);
    }

}

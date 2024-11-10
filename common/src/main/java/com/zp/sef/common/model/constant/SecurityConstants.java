package com.zp.sef.common.model.constant;

/**
 * SecurityConstants
 *
 * @author ZP
 */
public class SecurityConstants {

    public static final String BEARER_TOKEN_STARTER = "Bearer ";

    /**
     * token默认有效期，12小时, 毫秒
     */
    public static final long EXPIRATION_TIME = 12 * 60 * 60 * 1000L;

}

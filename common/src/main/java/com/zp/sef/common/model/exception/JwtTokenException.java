package com.zp.sef.common.model.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * JwtTokenException
 *
 * @author ZP
 */
public class JwtTokenException extends AuthenticationException {

    public JwtTokenException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtTokenException(String msg) {
        super(msg);
    }
}

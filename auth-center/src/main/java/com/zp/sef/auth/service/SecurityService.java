package com.zp.sef.auth.service;

/**
 * SecurityService
 *
 * @author ZP
 * @since 2024/10/22 11:22
 */
public interface SecurityService {

    /**
     * 用户认证，获取Authentication放入SecurityContextHolder
     *
     * @return Authentication
     */
    boolean authentication();

}

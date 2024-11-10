/*
 * chsi
 * Created on 2024-11-05
 */
package com.zp.sef.common.auth.storage;

/**
 * @author zp <a href="mailto:zoup@chsi.com.cn">zoupeng</a>
 * @version $Id$
 */
public interface TokenStorage {

    /**
     * 存储Token
     *
     * @param userId 用户ID
     * @param token 要存储的Token
     */
    void store(String userId, String token);

    /**
     * 验证Token是否有效
     *
     * @param userId 用户ID
     * @param token 要验证的Token
     * @return 如果Token有效返回true，否则返回false
     */
    boolean isValid(String userId, String token);

    /**
     * 对指定的Token续期
     *
     * @param userId 用户ID
     * @param token 要续期的Token
     * @throws Exception 如果Token不存在，则抛出异常
     */
    void renew(String userId, String token) throws Exception;


}

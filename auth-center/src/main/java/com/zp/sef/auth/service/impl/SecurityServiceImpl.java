package com.zp.sef.auth.service.impl;

import com.zp.sef.auth.service.SecurityService;
import org.springframework.stereotype.Service;

/**
 * SecurityServiceImpl
 *
 * @author ZP
 * @since 2024/10/22 11:24
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public boolean authentication() {
        return true;
    }
}

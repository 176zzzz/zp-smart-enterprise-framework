package com.zp.sef.common.auth.authentication.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zp.sef.common.auth.storage.TokenStorage;
import com.zp.sef.common.model.constant.SecurityConstants;
import com.zp.sef.common.model.exception.JwtTokenException;
import com.zp.sef.common.model.user.LoginUser;
import com.zp.sef.common.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JWTAuthorizationFilter
 *
 * @author ZP
 */
@Slf4j
public class JwtAuthenticationfilter extends OncePerRequestFilter {

    private final List<AntPathRequestMatcher> antPathRequestMatcherList;

    private TokenStorage tokenStorage;

    public JwtAuthenticationfilter(TokenStorage tokenStorage, String... antPatterns) {
        List<AntPathRequestMatcher> matchers = new ArrayList<>();
        // 将传入的路径模式添加到匹配器列表中
        for (String pattern : antPatterns) {
            if (StringUtils.isNotBlank(pattern)) {
                matchers.add(new AntPathRequestMatcher(pattern));
            }
        }
        this.antPathRequestMatcherList = matchers;
        this.tokenStorage = tokenStorage;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //如果在白名单中，不检查token
        if (match(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = getJwtFromRequest(request);

        if (StringUtils.isBlank(jwtToken)) {
            log.error("token is empty");
            throw new JwtTokenException("token is empty");
        }

        try {
            JwtTokenUtil.validateToken(jwtToken);
            LoginUser userDetails = JwtTokenUtil.getUserDetails(jwtToken);
            boolean valid = tokenStorage.isValid(userDetails.getId(), jwtToken);
            if (!valid) {
                log.error("token is invalid, tokenStorage dont have");
                throw new JwtTokenException("token is invalid");
            }
        } catch (ExpiredJwtException e) {
            log.error("token is expired");
            throw new JwtTokenException("token is expired");
        } catch (Exception e) {
            log.error("token is invalid, Exception type: " + e.getClass().getSimpleName() + ", Message: " + e.getMessage());
            throw new JwtTokenException(e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith(SecurityConstants.BEARER_TOKEN_STARTER)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private boolean match(HttpServletRequest request) {
        for (AntPathRequestMatcher antPathRequestMatcher : antPathRequestMatcherList) {
            if (antPathRequestMatcher.matcher(request).isMatch()) {
                return true;
            }
        }
        return false;
    }


}

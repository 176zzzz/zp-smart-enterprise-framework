/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zp.sef.common.auth.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zp.sef.common.model.enums.HttpStatusEnum;
import com.zp.sef.common.model.web.ResponseResult;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 处理SpringSecurity未认证异常
 *
 * @author ZP
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        LOGGER.error("Responding with unauthorized error. Message:{}, url:{}", e.getMessage(), request.getRequestURI());

        // 设置HTTP状态码
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // 设置响应类型为JSON
        response.setContentType("application/json");
        // 设置字符编码
        response.setCharacterEncoding("UTF-8");

        ResponseResult<Object> fail = ResponseResult.fail(HttpStatusEnum.UNAUTHORIZED.getCode(), e.getMessage());

        // 将响应体转换为JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(fail);

        // 将JSON写入响应
        try (PrintWriter writer = response.getWriter()) {
            writer.write(jsonResponse);
        }
    }
}

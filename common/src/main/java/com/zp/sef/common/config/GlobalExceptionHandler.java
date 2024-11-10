package com.zp.sef.common.config;


import com.zp.sef.common.model.exception.ServiceException;
import com.zp.sef.common.model.web.ResponseResult;
import feign.FeignException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * GlobalExceptionHandler
 *
 * @author ZP
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 参数校验异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseResult handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        String serviceExceptionMsg = "传参错误，请求地址：{}，报错为：{}";
        log.error(serviceExceptionMsg, requestUrl, e.getMessage());
        return ResponseResult.fail(e.getMessage());
    }


    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseResult handleServiceException(ServiceException e, HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        String serviceExceptionMsg = "请求发生业务异常，请求地址：{}，报错为：{}";
        log.error(serviceExceptionMsg, requestUrl, e.getMessage());
        return ResponseResult.fail(e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestUrl, e);
        return ResponseResult.fail(e.getMessage());
    }

    /**
     * feign调用出错
     */
    @ExceptionHandler(FeignException.class)
    public ResponseResult handleRuntimeException(FeignException e, HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        String serviceExceptionMsg = "feign调用发生异常，请求地址：{}，报错为：{}";
        log.error(serviceExceptionMsg, requestUrl, e.getMessage());
        return ResponseResult.fail(e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e, HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestUrl, e);
        return ResponseResult.fail(e.getMessage());
    }
}

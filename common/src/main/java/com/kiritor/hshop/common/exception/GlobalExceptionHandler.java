/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: GlobalExceptionHandler
 */
package com.kiritor.hshop.common.exception;

import com.kiritor.hshop.common.entity.ResultBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Class GlobalExceptionHandler
 * @Description 全局异常处理
 * @Author 子梁
 * @Date 2020/7/6
 * @Verson 1.0.0
 *
 */


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResultBody baseExceptionHandle(BaseException ex, HttpServletRequest request) {
        return ResultBody.fail(ex.getResultCode());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResultBody nullExceptionHandle(NullPointerException ex, HttpServletRequest request) {
        return ResultBody.fail("空指针异常");
    }

    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, Exception e){
        e.printStackTrace();
        return ResultBody.fail(e.getMessage());
    }
}

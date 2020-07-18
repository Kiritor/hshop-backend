/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: ResultResponse
 */
package com.kiritor.hshop.common.entity;

import com.kiritor.hshop.common.enums.ResultCode;
import com.kiritor.hshop.common.exception.BaseException;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

/**
 * @Class ResultResponse
 * @Description
 *    返回对象
 * @Author 子梁
 * @Date 2020/7/7
 * @Verson 1.0.0
 *
 */

@Data
public class ResultBody implements Serializable {
    /**
     * 代码
     */
    private int code;
    /**
     * 状态码
     */
    private int status;
    /**
     * 信息
     */
    private String message;
    /**
     * 路径
     */
    private String path;
    /**
     * 时间戳
     */
    private Instant timestamp;

    /**
     * 数据
     */
    private Object data;

    public ResultBody(int code, int status, String message, String path, Object data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = Instant.now();
        this.data = data;
    }

    public ResultBody(BaseException ex,String path) {
        this(ex.getResultCode().getCode(), ex.getResultCode().getStatus().value(), ex.getResultCode().getMessage(), path, ex.getData());
    }

    /**
     * 成功
     *
     * @return
     */
    public static ResultBody ok() {
        return ok(ResultCode.SUCCESS,null);
    }

    /**
     * 成功
     * @param resultCode
     * @return
     */
    public static ResultBody ok(ResultCode resultCode) {
        ResultBody rb = new ResultBody();
        rb.setCode(resultCode.getCode());
        rb.setStatus(resultCode.getStatus().value());
        rb.setMessage(resultCode.getMessage());
        return rb;
    }


    /**
     * 成功
     * @param data
     * @return
     */
    public static ResultBody ok(ResultCode resultCode,Object data) {
        ResultBody rb = new ResultBody();
        rb.setCode(resultCode.getCode());
        rb.setStatus(resultCode.getStatus().value());
        rb.setMessage(resultCode.getMessage());
        if(data!=null) {
            rb.setData(data);
        }
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody fail(ResultCode errorInfo) {
        ResultBody rb = new ResultBody();
        rb.setCode(errorInfo.getCode());
        rb.setMessage(errorInfo.getMessage());
        rb.setStatus(errorInfo.getStatus().value());
        rb.setData(null);
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody fail(ResultCode errorInfo,String message) {
        ResultBody rb = new ResultBody();
        rb.setCode(errorInfo.getCode());
        rb.setMessage(message);
        rb.setStatus(errorInfo.getStatus().value());
        rb.setData(null);
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody fail( String message) {
        ResultBody rb = new ResultBody();
        rb.setCode(-1);
        rb.setMessage(message);
        rb.setData(null);
        return rb;
    }

}

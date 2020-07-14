/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: BaseException
 */
package com.kiritor.hshop.common.exception;

import com.kiritor.hshop.common.enums.ResultCode;
import lombok.Getter;

/**
 * @Class BaseException
 * @Description 异常基类
 * @Author 子梁
 * @Date 2020/7/6
 * @Verson 1.0.0
 *
 */

@Getter
public class BaseException extends RuntimeException{
    private final ResultCode resultCode;
    private final Object data;

    public BaseException(ResultCode resultCode, Object data) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
        this.data = data;
    }

    public BaseException(ResultCode resultCode, Object data, Throwable cause) {
        super(resultCode.getMessage(), cause);
        this.resultCode = resultCode;
        this.data = data;
    }
}

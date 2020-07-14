/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: AuthLimitHandler
 */
package com.kiritor.hshop.serviceuser.security.handler;

import com.kiritor.hshop.common.entity.ResultBody;
import com.kiritor.hshop.common.enums.ResultCode;
import com.kiritor.hshop.common.utils.HttpResponseUitls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Class AuthLimitHandler
 * @Description
 * @Author 子梁
 * @Date 2020/7/10
 * @Verson 1.0.0
 *
 */

@Component
@Slf4j
public class MyAuthLimitHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        HttpResponseUitls.toJSONString(response,ResultBody.fail(ResultCode.NO_PERMISSION));

    }
}
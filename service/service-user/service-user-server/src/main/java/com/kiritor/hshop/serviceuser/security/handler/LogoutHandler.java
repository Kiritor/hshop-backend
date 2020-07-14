/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: LogoutHandler
 */
package com.kiritor.hshop.serviceuser.security.handler;

import com.kiritor.hshop.common.entity.ResultBody;
import com.kiritor.hshop.common.enums.ResultCode;
import com.kiritor.hshop.common.utils.HttpResponseUitls;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Class LogoutHandler
 * @Description 退出成功处理handler
 * @Author 子梁
 * @Date 2020/7/11
 * @Verson 1.0.0
 *
 */

public class LogoutHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HttpResponseUitls.toJSONString(httpServletResponse,ResultBody.ok(ResultCode.USER_LOGOUT_SUCCESS));
    }
}

/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: JWTAuthenticationFilter
 */
package com.kiritor.hshop.serviceuser.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kiritor.hshop.common.entity.ResultBody;
import com.kiritor.hshop.common.enums.ResultCode;
import com.kiritor.hshop.common.utils.HttpResponseUitls;
import com.kiritor.hshop.serviceuser.security.model.SecurityUser;
import com.kiritor.hshop.serviceuser.security.utils.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Class JWTAuthenticationFilter
 * @Description token验证过滤器
 * @Author 子梁
 * @Date 2020/7/12
 * @Verson 1.0.0
 *
 */

public class JWTAuthenticationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(JwtUtils.HEADER_TOKEN_NAME);
        /* token为null直接走登录的过滤器，不为空走下面 */
        if (token!=null&&token.trim().length()>0) {
            String tokenBody = null;
            try {
                tokenBody = JwtUtils.testJwt(token);
            } catch (Exception e) {
                e.printStackTrace();
            }
            /* 从token中取出用户信息，放在上下文中 */
            if (tokenBody!=null&&tokenBody.trim().length()>0){
                JSONObject user = JSON.parseObject(tokenBody).getJSONObject("user");
                if(user!=null) {
                    SecurityUser sysUser = JSONObject.parseObject(user.toString(), SecurityUser.class);
                    // SecurityUser sysUser = JSON.toJavaObject(user,SecurityUser.class);
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(sysUser, null, sysUser.getAuthorities()));
                }else {
                    HttpResponseUitls.toJSONString((HttpServletResponse) response,ResultBody.fail(ResultCode.AUTH_TOKEN_ERROR));
                    throw new AuthenticationException("token过期或无效");

                }
            }else{
                HttpServletResponse res = (HttpServletResponse) response;
                HttpResponseUitls.toJSONString(res,ResultBody.fail(ResultCode.AUTH_TOKEN_ERROR));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}

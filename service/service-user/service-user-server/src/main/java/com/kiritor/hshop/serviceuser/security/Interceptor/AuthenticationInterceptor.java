/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: AuthenticationInterceptor
 */
package com.kiritor.hshop.serviceuser.security.Interceptor;

import com.kiritor.hshop.common.utils.JwtUtils;
import com.kiritor.hshop.serviceuser.security.utils.PassToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Method;

/**
 * @Class AuthenticationInterceptor
 * @Description
 * @Author 子梁
 * @Date 2020/7/12
 * @Verson 1.0.0
 *
 */

@Component
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader(JwtUtils.HEADER_TOKEN_NAME);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON);
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                if(token==null){
                    //HttpResponseUitls.toJSONString(httpServletResponse,ResultBody.fail(ResultCode.AUTH_TOKEN_ERROR));
                    throw  new Exception("token为空!");
                }
               Object obj =  JwtUtils.testJwt(token);
               if(obj==null) {
                   throw new Exception("token失效过期");
               }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: HttpResponseUitls
 */
package com.kiritor.hshop.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.kiritor.hshop.common.entity.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Class HttpResponseUitls
 * @Description
 * @Author 子梁
 * @Date 2020/7/10
 * @Verson 1.0.0
 *
 */
@Slf4j
public class HttpResponseUitls {
    /**
     * 输出JSON
     * @param response
     * @param result
     * @throws IOException
     */
    public static void toJSONString(HttpServletResponse response, ResultBody result) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONObject.toJSONString(result));
    }
}

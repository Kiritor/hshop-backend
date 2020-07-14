/**
 * Copyright (C), 2014-2020, xx有限公司
 * FileName: PasswordUtils
 */
package com.kiritor.hshop.serviceuser.security.utils;

import com.kiritor.hshop.common.enums.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;

/**
 * @Class PasswordUtils
 * @Description 加密工具类
 * @Author 子梁
 * @Date 2020/7/10
 * @Verson 1.0.0
 *
 */

@Slf4j
public class PasswordUtils {
    /**
     * 校验密码是否一致
     *
     * @param password: 前端传过来的密码
     * @param hashedPassword：数据库中储存加密过后的密码
     * @param salt：盐值
     * @return
     */
    public static boolean isValidPassword(String password, String hashedPassword, String salt) {
        return hashedPassword.equalsIgnoreCase(encodePassword(password, salt));
    }

    /**
     * 通过SHA1对密码进行编码
     *
     * @param password：密码
     * @param salt：盐值
     * @return
     */
    public static String encodePassword(String password, String salt) {
        String encodedPassword;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            if (salt != null) {
                digest.reset();
                digest.update(salt.getBytes());
            }
            byte[] hashed = digest.digest(password.getBytes());
            int iterations = Constants.HASH_ITERATIONS - 1;
            for (int i = 0; i < iterations; ++i) {
                digest.reset();
                hashed = digest.digest(hashed);
            }
            encodedPassword = new String(Hex.encode(hashed));
        } catch (Exception e) {
            log.error("加密异常:", e);
            return null;
        }
        return encodedPassword;
    }


    public static void main(String[] args){
        System.out.println(PasswordUtils.encodePassword("liangtao","hshop"));

    }

}

package com.learn.learnsatoken.util;

import org.springframework.util.DigestUtils;

/**
 * MD5工具类
 *
 * @author Peter Cheung
 * @since 2023-07-19 11:37:24
 */
public class MD5Util {
    /**
     * 生成md5
     */
    public static String toMD5(String str, String salt) {
        String base = str + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
}

package com.learn.learnsatoken.util;

import java.util.UUID;

/**
 * 创造simple-uuid
 *
 * @author Peter Cheung
 * 2023/4/26 13:26
 */
public class UUIDUtil {
    public static String toUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

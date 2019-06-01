package com.burton.sale.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @author Burton
 * @location：com.burton.sale.utils.KeyUtil
 * @title: KeyUtil
 * @projectName sale
 * @description:
 * @date 2019/5/29 14:58
 */
public class KeyUtil {

    /**
     * 生成唯一的key
     * 格式：时间+随机数
     *
     * @return
     */
    public static String generateUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

    /**
     * 获取uuid
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        return uuidStr;
    }
}

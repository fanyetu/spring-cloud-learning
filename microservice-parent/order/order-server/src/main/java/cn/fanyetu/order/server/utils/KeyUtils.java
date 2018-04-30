package cn.fanyetu.order.server.utils;

import java.util.Random;

/**
 * @author zhanghaonan
 * @date 2018/4/19
 */
public class KeyUtils {

    /**
     * 生成唯一主键
     *
     * @return
     */
    public static synchronized String generateUniqueKey() {
        Random random = new Random();
        int number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}

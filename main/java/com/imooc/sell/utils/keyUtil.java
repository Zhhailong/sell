package com.imooc.sell.utils;

import java.util.Random;

/**
 * @Auther: zhanghailong
 * @Date: 2018/6/25 23:19
 * @Description:
 */
public class keyUtil {

    /**生成唯一主键
     *
     * 格式: 时间+随机数
     * */
    public static synchronized String genUniqueKey(){

        Random random = new Random();

        Integer num = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(num);
    }
}

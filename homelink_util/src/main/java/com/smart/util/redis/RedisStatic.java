package com.smart.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
/**
 * 静态方法取得redisUtil对象辅助类
 * @Author: lh 
 * @Date: 2018/4/5 19:07
 * @ClassName: RedisStatic
 */
@Component
@ConditionalOnBean(RedisUtil.class)
public class RedisStatic {
    private static RedisUtil redisUtil = null;

    public static RedisUtil getRedisUtil() {
        return  RedisStatic.redisUtil;
    }
    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        RedisStatic.redisUtil = redisUtil;
    }
}

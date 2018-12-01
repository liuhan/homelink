package com.smart.util.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 描述：<br>
 * 创建人：Created by syl on 2017/12/11.
 * 创建时间：2017/12/6 17:33<br>
 * @version v1.0
 */

/*@Configuration
@EnableAutoConfiguration*/
public class RedisSingleConfiguration {
    Logger logger = LoggerFactory.getLogger(RedisSingleConfiguration.class);

    @Value("test.tanlangui.org")
    private String host;

    @Value("6397")
    private int port;

    @Value("5000")
    private int timeout;

    @Value("8")
    private int maxIdle;

    @Value("10000")
    private long maxWaitMillis;

    @Value("test")
    private String password;


    @Bean
    public JedisPool redisPoolFactory() {
        logger.info("JedisPoolsdsd 登陆是  ！！");
        logger.info("redis地址：" + host + ":" + port);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "test.tanlangui.org",6379 ,5000, "test");
        return jedisPool;
    }

}
package com.morningstar.kill.config;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.junit.jupiter.api.Test;


@SpringBootTest
public class TestRedisCache {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void test(){
        redisTemplate.opsForValue().set("name","henry");
        assert "henry".equals(redisTemplate.opsForValue().get("name"));
    }
}

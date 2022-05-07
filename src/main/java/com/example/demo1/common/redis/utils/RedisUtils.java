package com.example.demo1.common.redis.utils;

import cn.hutool.core.util.ObjectUtil;
import com.example.demo1.common.redis.config.RedisProperties;
import com.example.demo1.utils.json.JsonUtils;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * @author cyj
 *
 * redis 工具类
 */
@Component
@NoArgsConstructor
public class RedisUtils {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * true 用户查询
     * false 验证码查询
     * */
    public String get(String uuid, Boolean timeout) {
        String redisKey = formatKey(uuid,timeout);
        return stringRedisTemplate.opsForValue().get(redisKey);
    }

    /**
     * true 用户存储
     * false 验证码存储
     *
     * value instanceof CharSequence 判断是否为字符串 抄至ObjectUtil.isEmpty(value)
     * 不要单纯用  stringRedisTemplate.opsForValue().set(key,value,long)  value全是空格，用下面的Duration.ofMillis(long) 已秒为单位
     * */
    public void set(String uuid, Object value, Boolean timeout) {
        String redisKey = formatKey(uuid,timeout);
        if (ObjectUtil.isEmpty(value)){
            throw new RuntimeException("redis存储不能为空");
        }
        stringRedisTemplate.opsForValue().set(redisKey, value instanceof CharSequence ? (String) value :JsonUtils.toJsonString(value),
                Duration.ofMillis(timeout?RedisProperties.REDIS_ID_USER_TIME:RedisProperties.REDIS_ID_TIME));
    }

    /**
     * true 用户删除
     * false 验证码删除
     * */
    public void delete(String uuid, Boolean timeout) {
        String redisKey = formatKey(uuid,timeout);
        stringRedisTemplate.delete(redisKey);
    }

    /**
     * true 用户id
     * false 验证码id
     * */
    private String formatKey(String uuid, Boolean timeout) {
        if (timeout){
            return uuid + RedisProperties.ID_NAME;
        }else {
            return RedisProperties.ID_NAME;
        }
    }
}
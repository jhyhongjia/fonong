package com.tencent.wxcloudrun.tool.utils;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    private static final String CACHE_KEY_SEPARATOR = ".";

    //构建缓存key
    public String buildKey(String... strObjs) {
        //将多个字符串之间用点拼接
        return Stream.of(strObjs).collect(Collectors.joining(CACHE_KEY_SEPARATOR));
    }

    // 设置值
    public void set(String key, Object value) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(key, value);
    }

    // 获取值
    public Object get(String key) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        return ops.get(key);
    }

    // 设置带过期时间的值
    public void setEx(String key, Object value, long timeout, TimeUnit timeUnit) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(key, value, timeout, timeUnit);
    }

    // 删除指定的键
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    // 检查某个键是否存在
    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    // 设置hash值
    public void hSet(String hashKey, String key, Object value) {
        redisTemplate.opsForHash().put(hashKey, key, value);
    }

    // 获取hash值
    public Object hGet(String hashKey, String key) {
        return redisTemplate.opsForHash().get(hashKey, key);
    }

    // 删除hash值
    public Long hDel(String hashKey, String key) {
        return redisTemplate.opsForHash().delete(hashKey, key);
    }

    // 获取指定范围内的hash值
    public Map<Object, Object> hGetAll(String hashKey) {
        return redisTemplate.opsForHash().entries(hashKey);
    }

    // 设置列表值（左侧插入）
    public void lPush(String key, Object value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    // 获取列表中的元素
    public List<Object> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    // 删除列表中的元素
    public void lRemove(String key, long count, Object value) {
        redisTemplate.opsForList().remove(key, count, value);
    }

    // 获取Set中的所有元素
    public Set<Object> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    // 向Set中添加元素
    public void sAdd(String key, Object... values) {
        redisTemplate.opsForSet().add(key, values);
    }

    // 从Set中移除元素
    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    // 获取SortedSet中的元素
    public Set<Object> zRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    // 向SortedSet中添加元素
    public void zAdd(String key, Object value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    // 从SortedSet中移除元素
    public Long zRemove(String key, Object... values) {
        return redisTemplate.opsForZSet().remove(key, values);
    }

    // 设置key的过期时间
    public Boolean expire(String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    // 获取key的过期时间
    public Long getExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }
}

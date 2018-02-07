package com.lhl.redis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by liuhonglin on 2015/9/12.
 */
public class RedisClient {

    private final String REDIS_HOST = "127.0.0.1";
    private final int    REDIS_PORT = 6379;
    private final int  CONN_TIMEOUT = 60000;
    private final int  DEFAULT_EXPIRE = 2 * 24 * 60 * 60;   //默认过期时间48小时，单位：秒。

    private Jedis redis = null;

    public RedisClient() {
        redis = new Jedis(REDIS_HOST, REDIS_PORT, CONN_TIMEOUT);
    }

    /**
     * 保存 String 类型数据，默认过期时间48小时。
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        set(key, DEFAULT_EXPIRE, value);
    }

    /**
     * 保存 String 类型数据，并设置过期时间。
     * @param key
     * @param expire 过期时间，单位秒
     * @param value
     */
    public void set(String key, int expire, String value) {
        redis.setex(key, expire, value);
    }

    /**
     * 一次设置多个key-value
     * @param key_vaule "key","value"对应间隔的字符串
     */
    public void mset(String... key_vaule) {
        redis.mset(key_vaule);
    }
    /**
     * 一次设置多个key-value
     * @param mKeyValue
     */
    public void mset(Map<String, String> mKeyValue) {
        Set<String> keys = mKeyValue.keySet();
        for(String key : keys) {
            set(key, mKeyValue.get(key));
        }
    }

    /**
     * 给字符串追加内容
     * @param key
     * @param value
     */
    public void append(String key, String value) {
        redis.append(key, value);
    }

    /**
     * 获取可以对应的值
     * @param key
     * @return
     */
    public String get(String key) {
        return redis.get(key);
    }

    /**
     * 一次获取多个key的值
     * @param keys
     * @return
     */
    public List<String> mget(String... keys) {
        return redis.mget(keys);
    }

    /**
     * 保存 hashs 类型数据，给 Hash 添加 field-value
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key, String field, String value) {
        redis.hset(key, field, value);
    }

    /**
     * 批量设置 Hashs 类型的值
     * @param key
     * @param value
     */
    public void hmset(String key, Map<String, String> value) {
        redis.hmset(key, value);
    }

    /**
     * 获取 key 中 field 对应的值
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        return redis.hget(key, field);
    }

    /**
     * 获取 key 中多个 field 对应的值
     * @param key
     * @param fields
     * @return
     */
    public List<String> hmget(String key, String... fields) {
        return redis.hmget(key, fields);
    }

    /**
     * 获取 hash 中多有 key 的值
     * @param key
     * @return
     */
    public Map<String, String> hgetAll(String key) {
        return redis.hgetAll(key);
    }

    /**
     * 在 list(链表)首部添加元素
     * @param key
     * @param values
     */
    public void lpush(String key, String... values) {
        redis.lpush(key, values);
    }

    /**
     * 在 list(链表)尾部添加元素
     * @param key
     * @param values
     */
    public void rpush(String key, String... values) {
        redis.rpush(key, values);
    }

    /**
     * 在 list(链表)首部删除元素
     * @param key
     * @return
     */
    public String lpop(String key) {
        return redis.lpop(key);
    }

    /**
     * 在 list(链表)尾部删除元素
     * @param key
     * @return
     */
    public String rpop(String key) {
        return redis.rpop(key);
    }

    /**
     * 获取 list 指定区间的元素
     * @param key
     * @param index
     * @param end
     * @return
     */
    public List<String> lrang(String key, int index, int end) {
        return redis.lrange(key, index, end);
    }

    /**
     * 获得 list 的大小
     * @param key
     * @return
     */
    public Long llen(String key) {
        return redis.llen(key);
    }

    /**
     * 保存 set 类型数据，存储没有重复元素的集合
     * @param memberKey
     * @param members
     */
    public Long sadd(String memberKey, String... members){
        return redis.sadd(memberKey, members);
    }

    /**
     * 从 set 中移除元素
     * @param memberKey
     * @param members
     */
    public Long srem(String memberKey, String... members) {
        return redis.srem(memberKey, members);
    }

    /**
     * 枚举出 set 中的元素
     * @param memberKey
     * @return
     */
    public Set<String> smembers(String memberKey) {
        return redis.smembers(memberKey);
    }

    /**
     * 插入 sort set ,并指定元素的序号
     * @param memberKey
     * @param no
     * @param member
     * @return
     */
    public Long zadd(String memberKey, double no, String member) {
        return redis.zadd(memberKey, no, member);
    }

    /**
     * 根据范围取 set
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrange(String key, long start, long end) {
        return redis.zrange(key, start, end);
    }

    /**
     * 根据范围反向取 set
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrevrange(String key, long start, long end) {
        return redis.zrevrange(key, start, end);
    }

    /**
     * 删除指定元素
     * @param keys
     * @return
     */
    public Long del(String... keys) {
        return redis.del(keys);
    }

    /**
     * 返回 Jedis 对象
     * @return
     */
    public Jedis getRedis() {
        return redis;
    }
}

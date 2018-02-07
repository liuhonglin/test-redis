package com.lhl.redis;

import redis.clients.jedis.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by liuhonglin on 2015/8/30.
 */
public class Tester {

    public static void main(String... args) {
        RedisClient client = new RedisClient();
        client.set("testkey1", "value1");
        System.out.println(client.get("testkey1"));

        client.hset("user", "id", "123");
        client.hset("user", "name", "mr.li");
        client.hset("user", "age", "18");

        String userName = client.hget("user", "name");
        System.out.println("hash set - userName = " + userName);

        String url = client.hget("links:urls", "1");
        System.out.println("hash set - url = " + url);

        Jedis redis = new Jedis("127.0.0.1", 6379, 6000);
        redis.ping();

        Pipeline redisPipeline = redis.pipelined();

    }


}

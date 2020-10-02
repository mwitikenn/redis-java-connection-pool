package com.indepthkenya.connectionpooling.resources;


import com.indepthkenya.connectionpooling.annotations.Redis;
import com.indepthkenya.connectionpooling.exception.JedisConnectionNotFound;
import com.indepthkenya.connectionpooling.exception.JedisPoolNotFoundException;
import org.reflections.Reflections;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CacheFactory {
    Map<String, JedisPool> jedisPoolMap = new HashMap<>();

    public void createRedisPool(String packageName) {
        Reflections reflections = new Reflections(packageName);

        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Redis.class);
        for (Class<?> cl : classes) {
            Redis redis = cl.getAnnotation(Redis.class);
            //jedisPool
            try {

                JedisPoolConfig poolConfig = new JedisPoolConfig();
                poolConfig.setMaxTotal(redis.maxTotal());
                poolConfig.setMaxIdle(redis.maxIdle());
                poolConfig.setMinIdle(redis.minIdle());
                poolConfig.setTestOnBorrow(redis.isTestOnBorrow());
                poolConfig.setTestOnReturn(redis.isTestOnReturn());
                poolConfig.setTestWhileIdle(redis.isTestWhileIdle());
                poolConfig.setMinEvictableIdleTimeMillis(redis.minEvictableIdleTimeMillis());
                poolConfig.setTimeBetweenEvictionRunsMillis(redis.timeBetweenEvictionRunsMillis());
                poolConfig.setNumTestsPerEvictionRun(redis.NumTestsPerEvictionRun());
                poolConfig.setBlockWhenExhausted(redis.isBlockWhenExhausted());

                JedisPool jedisPool = new JedisPool(poolConfig, redis.host(), redis.port(), 2000, redis.password());

                this.jedisPoolMap.put(redis.id(), jedisPool);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw ex;
            }

        }
        System.out.println(jedisPoolMap);
    }

    public JedisPool getJedisPool() throws JedisPoolNotFoundException {
        JedisPool jedisPool = this.jedisPoolMap.get("1");
        if (jedisPool == null) {
            throw new JedisPoolNotFoundException("Jedis pool not found!");
        }
        return jedisPool;
    }

    public JedisPool getRedisPool(String jedisPoolId) throws JedisPoolNotFoundException {
        JedisPool jedisPool = this.jedisPoolMap.get(jedisPoolId);
        if (jedisPool == null) {
            throw new JedisPoolNotFoundException("Jedis pool not found!");
        }
        return jedisPool;
    }

    public Jedis getRedisConnection() throws JedisConnectionNotFound {
        Jedis jedis = this.jedisPoolMap.get("1").getResource();
        if (jedis == null) {
            throw new JedisConnectionNotFound("Jedis connection not found!");
        }
        return jedis;
    }

    public Jedis getRedisConnection(String jedisPoolId) throws JedisConnectionNotFound {
        Jedis jedis = this.jedisPoolMap.get(jedisPoolId).getResource();
        if (jedis == null) {
            throw new JedisConnectionNotFound("Jedis connection not found!");
        }
        return jedis;
    }
}

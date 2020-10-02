package com.indepthkenya.connectionpooling;

import com.indepthkenya.connectionpooling.exception.JedisConnectionNotFound;
import com.indepthkenya.connectionpooling.resources.CacheFactory;
import redis.clients.jedis.Jedis;

/**
 * Hello world!
 *
 */
public class App
{
    String packageName = this.getClass().getPackageName();
    static CacheFactory cacheFactory = new CacheFactory();
    public App(){
        cacheFactory.createRedisPool(packageName);
    }
    public static void main(String[] args )
    {
        try {
            App app = new App();
        } catch (Exception ex) {

        }
    }

    //Get first connection -default
    public static Jedis getRedisConnection() throws JedisConnectionNotFound {
        return cacheFactory.getRedisConnection();
    }

    //Get - a connecion from a specific redis installation if there are many
    public static Jedis getRedisConnection(String redisInstance) throws JedisConnectionNotFound {
        return cacheFactory.getRedisConnection("2");
    }
}

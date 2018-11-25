package com.important.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

	public static void main(String[] args) {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		try (JedisPool jedisPool = new JedisPool(poolConfig, "47.101.146.8", 6379);
				Jedis jedis = jedisPool.getResource();) {
			// ...
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

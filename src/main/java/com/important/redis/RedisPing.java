package com.important.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class RedisPing {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("47.101.146.8", 6379);
		// 看是否能ping通
		System.out.println(jedis.ping());

		// test api
		jedis.set("jedis", "testJedis");
		System.out.println(jedis.get("jedis"));

		// 开启事务
		Transaction multi = jedis.multi();
		multi.set("k1", "v1");
		multi.set("k2", "v2");
		multi.exec();
//		multi.discard();

		jedis.close();
	}

}

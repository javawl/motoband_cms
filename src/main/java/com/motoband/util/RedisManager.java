package com.motoband.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;



public class RedisManager {

	private static RedisManager redisManager;

	private ConcurrentHashMap<String, JedisPool> poolMap = new ConcurrentHashMap<String, JedisPool>();

	public static RedisManager getInstance() {
		if (redisManager == null) {
			redisManager = new RedisManager();
		}
		return redisManager;
	}

	private RedisManager() {
		JedisPoolConfig config = new JedisPoolConfig();

		JedisPool followPool = new JedisPool(config, Consts.REDIS_FOLLOW_IP, Consts.REDIS_FOLLOW_PORT, Consts.REDIS_FOLLOW_TIMEOUT, Consts.REDIS_FOLLOW_AUTH);
		poolMap.put(Consts.REDIS_SCHEME_FOLLOW, followPool);

		JedisPool userPool = new JedisPool(config, Consts.REDIS_USER_IP, Consts.REDIS_USER_PORT, Consts.REDIS_USER_TIMEOUT, Consts.REDIS_USER_AUTH);
		poolMap.put(Consts.REDIS_SCHEME_USER, userPool);

		JedisPool rideDataPool = new JedisPool(config, Consts.REDIS_RIDEDATA_IP, Consts.REDIS_RIDEDATA_PORT, Consts.REDIS_RIDEDATA_TIMEOUT, Consts.REDIS_RIDEDATA_AUTH);
		poolMap.put(Consts.REDIS_SCHEME_RIDEDATA, rideDataPool);

		JedisPool newsPool = new JedisPool(config, Consts.REDIS_NEWS_IP, Consts.REDIS_NEWS_PORT, Consts.REDIS_NEWS_TIMEOUT, Consts.REDIS_NEWS_AUTH);
		poolMap.put(Consts.REDIS_SCHEME_NEWS, newsPool);
        
		JedisPool runPool = new JedisPool(config, Consts.REDIS_RUN_IP, Consts.REDIS_RUN_PORT, Consts.REDIS_RUN_TIMEOUT, Consts.REDIS_RUN_AUTH);
		poolMap.put(Consts.REDIS_SCHEME_RUN, runPool);
		
		/*
		 * JedisPool rallyPool = new JedisPool(config, Consts.REDIS_RALLY_IP,
		 * Consts
		 * .REDIS_RALLY_PORT,Consts.REDIS_RALLY_TIMEOUT,Consts.REDIS_RALLY_AUTH
		 * ); poolMap.put(Consts.REDIS_SCHEME_RALLY, rallyPool);
		 */

	}

	/**
	 * 
	 * @Description key得剩余过期时间
	 * @param schemeName
	 * @param key
	 * @return 以秒为单位的整数值
	 */
	public long ttl(String schemeName, String key) {
		Jedis jedis = null;
		JedisPool pool = null;
		try {
			pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			long b = jedis.ttl(key);
			return b;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	/***
	 * 测试制定key是否存在
	 * 
	 * @param key
	 * @return
	 */
	public boolean checkkey(String schemeName, String key) {
		Jedis jedis = null;
		JedisPool pool = null;
		try {
			pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			boolean b = jedis.exists(key);
			return b;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/***
	 * 根据key删除
	 * 
	 * @param key
	 * @return 0key不存在
	 */
	public long delbykey(String schemeName, String key) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			long i = jedis.del(key);
			return i;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/***
	 * 添加字符串
	 * 
	 * @param key
	 * @param value
	 */
	public void string_set(String schemeName, String key, String value) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			jedis.set(key, value);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/***
	 * 获取字符串
	 * 
	 * @param schemeName
	 * @param key
	 * @return
	 */
	public String string_get(String schemeName, String key) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			String string = jedis.get(key);
			return string;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}

	/***
	 * 对list左侧插入元素
	 * 
	 * @param key
	 * @param val
	 */

	public boolean lpush(String schemeName, String key, String val) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			long i = jedis.lpush(key, val);
			if (i == 0) {
				return false;
			} else {
				return true;
			}
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}

	/***
	 * list右侧插入
	 * 
	 * @param schemeName
	 * @param key
	 * @param val
	 * @return
	 */
	public boolean rpush(String schemeName, String key, String val) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			long i = jedis.rpush(key, val);
			if (i == 0) {
				return false;
			} else {
				return true;
			}
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}

	/***
	 * list长度
	 * 
	 * @param schemeName
	 * @param key
	 * @return
	 */
	public long llen(String schemeName, String key) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			long ll = jedis.llen(key);
			return ll;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}

	/***
	 * list左侧删除
	 * 
	 * @param schemeName
	 * @param key
	 * @return
	 */
	public String lpop(String schemeName, String key) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			String value = jedis.lpop(key);
			return value;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}

	/***
	 * list右侧删除
	 * 
	 * @param schemeName
	 * @param key
	 * @return
	 */
	public String rpop(String schemeName, String key) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			String value = jedis.rpop(key);
			return value;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}

	/***
	 * 从list中删除全部value=val
	 * 
	 * @param schemeName
	 * @param key
	 * @param val
	 * @return
	 */
	public boolean lrem(String schemeName, String key, String val) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			long b = jedis.lrem(key, 0, val);
			if (b == 0) {
				return false;
			} else {
				return true;
			}
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}

	/***
	 * 从list中获取
	 * 
	 * @param schemeName
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> lrang(String schemeName, String key, long start, long end) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			List<String> userlist = jedis.lrange(key, start, end);
			return userlist;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}

	/***
	 * 获取list
	 * 
	 * @param schemeName
	 * @param key
	 * @return
	 */
	public List<String> lgetList(String schemeName, String key) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			long len = jedis.llen(key);
			List<String> ret = jedis.lrange(key, 0, len - 1);
			return ret;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/***
	 * 检测list是否存在value
	 * 
	 * @param schemeName
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean lexist(String schemeName, String key, String value) {
		List<String> list = lgetList(schemeName, key);
		return list.contains(value);
	}
	/***
	 * 检测zset是否存在value
	 * 
	 * @param schemeName
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean zexist(String schemeName, String key, String value) {
		Jedis jedis = null;
		boolean flag=false;
		Double scoreDouble = 0.0;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			scoreDouble = jedis.zscore(key, value);
			if(scoreDouble!=null){
				flag=true;
			}
			
		} finally {
			if (jedis != null) {
				jedis.close();
			}
			
		}
		return flag;
	}

	/***
	 * 将list整体放入
	 * 
	 * @param schemeName
	 * @param key
	 * @param list
	 */
	public void allpush(String schemeName, String key, ArrayList<String> list) {
		for (String string : list) {
			lpush(schemeName, key, string);
		}
	}

	/***
	 * hash添加
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return 
	 */
	public Long hset(String schemeName, String key, String field, String value) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			return jedis.hset(key, field, value);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/***
	 * hash获取
	 * 
	 * @param schemeName
	 * @param key
	 * @param field
	 * @return
	 */
	public String hget(String schemeName, String key, String field) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			String remark = jedis.hget(key, field);
			return remark;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/***
	 * hash获取全部
	 * 
	 * @param schemeName
	 * @param key
	 * @return
	 */
	public Map<String, String> hgetAll(String schemeName, String key) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			Map<String, String> map = jedis.hgetAll(key);
			if (map.isEmpty()) {
				return null;
			}
			return map;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}

	/***
	 * 删除hash
	 * 
	 * @param schemeName
	 * @param key
	 * @return
	 */
	public long hdel(String schemeName, String key) {
		Jedis jedis = null;
		long result = 0;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			
				result = jedis.del(key);


		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return result;
	}

	/***
	 * 删除hash中某一元素
	 * 
	 * @param schemeName
	 * @param key
	 * @param field
	 * @return
	 */
	public long hdelElement(String schemeName, String key, String field) {
		Jedis jedis = null;
		long result = 0;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			if (hexists(schemeName, key, field)) {
				result = jedis.hdel(key, field);
			}

		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return result;
	}

	/***
	 * 检测hash是否存在field
	 * 
	 * @param schemeName
	 * @param key
	 * @param field
	 * @return
	 */
	public boolean hexists(String schemeName, String key, String field) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			boolean keyhasfield = jedis.hexists(key, field);
			return keyhasfield;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/***
	 * 将map整体放入到hash中
	 * 
	 * @param schemeName
	 * @param key
	 * @param map
	 * @return
	 */
	public String hmset(String schemeName, String key, Map<String, String> map) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			String result = jedis.hmset(key, map);
			return result;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public long hlen(String schemeName, String key) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			long result = 0;
			if (checkkey(schemeName, key)) {
				result = jedis.hlen(key);
			}

			return result;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/***
	 * sorted set 添加
	 * 
	 * @param schemeName
	 * @param key
	 * @param score
	 * @param value
	 * @return
	 */
	public long zadd(String schemeName, String key, long score, String value) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			long addrow = jedis.zadd(key, score, value);
			return addrow;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/***
	 * sorted set 删除
	 * 
	 * @param schemeName
	 * @param key
	 * @param value
	 * @return
	 */
	public long zrem(String schemeName, String key, String value) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			long delrow = 0;

			delrow = jedis.zrem(key, value);

			return delrow;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}

	/***
	 * 返回制定元素在sorted set中的下标
	 * 
	 * @param schemeName
	 * @param key
	 * @param value
	 * @return
	 */
	public Long zrank(String schemeName, String key, String value) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			Long index = null;
			if (checkkey(schemeName, key)) {
				index = jedis.zrank(key, value);
			}

			return index;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/***
	 * 返回指定元素在sorted set中的下标 倒序
	 * 
	 * @param schemeName
	 * @param key
	 * @param value
	 * @return
	 */
	public long zrevrank(String schemeName, String key, String value) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			long index = jedis.zrevrank(key, value);
			return index;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/***
	 * 获取sorted set 元素数量
	 * 
	 * @param schemeName
	 * @param key
	 * @return
	 */

	public long zcard(String schemeName, String key) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			long count = jedis.zcard(key);
			return count;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/***
	 * 返回集合中指定“下标”区间的元素 返回的是有序结果
	 * 
	 * @param schemeName
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> zrangebyindex(String schemeName, String key, long start, long end) {
		Jedis jedis = null;
		Set<String> result = new HashSet<String>();
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			result = jedis.zrange(key, start, end);
			return result;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/***
	 * 返回集合中指定“下标”区间的元素 返回的是有序结果 倒序
	 * 
	 * @param schemeName
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> zrevrangebyindex(String schemeName, String key, long start, long end) {
		Jedis jedis = null;
		Set<String> result = new HashSet<String>();
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			result = jedis.zrevrange(key, start, end);
			return result;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/***
	 * 返回集合中指定“score”区间的元素
	 * 
	 * @param schemeName
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> zrangbyscore(String schemeName, String key, long min, long max) {
		Jedis jedis = null;
		Set<String> result = new HashSet<String>();
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			result = jedis.zrangeByScore(key, min, max);
			return result;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/***
	 * 返回集合中score在给定区间的数量
	 * 
	 * @param schemeName
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public long zcount(String schemeName, String key, Double min, Double max) {
		Jedis jedis = null;
		long rowcount = 0;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			rowcount = jedis.zcount(key, min, max);
			return rowcount;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/***
	 * 返回给定元素对应的score
	 * 
	 * @param schemeName
	 * @param key
	 * @param value
	 * @return
	 */
	public double zscore(String schemeName, String key, String value) {
		Jedis jedis = null;
		Double scoreDouble = 0.0;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			scoreDouble = jedis.zscore(key, value);
			return scoreDouble;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	
	/**
	 * @param schemeName
	 * @param key
	 * @param unixTime 毫秒
	 * @return
	 */
	public long setExpireAt(String schemeName, String key, long unixTime) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			long result = jedis.pexpireAt(key, unixTime);

			return result;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	
	public long sadd(String schemeName, String key, String value) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			long addrow = jedis.sadd(key, value);
			return addrow;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	
	public long srem(String schemeName, String key, String value) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			long delrow = 0;

			delrow = jedis.srem(key, value);

			return delrow;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}
	
	public String srandmember(String schemeName, String key) {
		List<String> res=srandmember(schemeName, key,1);
		if(res!=null&&res.size()>0) {
			return res.get(0);
		}
		return null;	
	}
	
	/**
	 * 从set集合中随机获取count个元素
	 * @param schemeName
	 * @param key
	 * @param count
	 * @return
	 */
	public List<String> srandmember(String schemeName, String key,int count) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			return jedis.srandmember(key, count);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}
	

	/***
	 * 返回集合中指定“score”区间的元素 倒序
	 * 
	 * @param schemeName
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> zrevrangeByScore(String schemeName, String key, long min, long max) {
		Jedis jedis = null;
		Set<String> result = new HashSet<String>();
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();

			result = jedis.zrevrangeByScore(key, max, min);
			return result;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public long scard(String schemeName, String key) {
		Jedis jedis = null;
		try {
			JedisPool pool = poolMap.get(schemeName);
			jedis = pool.getResource();
			return jedis.scard(key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
}

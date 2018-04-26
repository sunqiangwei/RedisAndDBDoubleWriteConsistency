package com.bixkjwfnh.eshop.cache.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * Redis操作DAO类
 * @since 2018.04.26
 * @author SunQW
 *
 */
@Repository
public class RedisDao {
	
	@Autowired
	private StringRedisTemplate template;

	/**
	 * 设置缓存
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		ValueOperations<String, String> ops = template.opsForValue();
		ops.set(key, value);
	}

	/**
	 * 获得缓存数据
	 * @param key
	 * @return
	 */
	public String get(String key) {
		ValueOperations<String, String> ops = this.template.opsForValue();
		return ops.get(key);
	}
	
	/**
	 * 删除缓存数据
	 * @param key
	 */
	public void delete(String key) {
		this.template.delete(key);
	}
}
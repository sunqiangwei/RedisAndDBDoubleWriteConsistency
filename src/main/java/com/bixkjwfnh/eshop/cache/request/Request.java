package com.bixkjwfnh.eshop.cache.request;

/**
 * 队列中的请求接口
 * 
 * @since 2018.04.26
 * @author SunQW
 *
 */
public interface Request {

	/**
	 * 请求中具体进行处理逻辑的方法
	 */
	public void process();
	
	public Integer getUniqueId();
	
}

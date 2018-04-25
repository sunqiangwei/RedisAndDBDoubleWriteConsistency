package com.bixkjwfnh.eshop.cache.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bixkjwfnh.eshop.cache.request.Request;
import com.bixkjwfnh.eshop.cache.request.RequestQueueHolder;

/**
 * 请求处理线程池：单例
 * 
 * @author SunQW
 */
public class ReuqestProcessThreadPool {

	private static final ReuqestProcessThreadPool instance = new ReuqestProcessThreadPool();
	
	/**
	 * 线程池
	 */
	private ExecutorService executorService = Executors.newFixedThreadPool(10);
	
	/**
	 * 进行请求队列的初始化，此处为10个请求队列，每个请求队列100条记录。
	 * ArrayBlockingQueue
	 * Blocking就是说明，如果队列满了，或者是空的，那么都会在执行操作的时候，阻塞住
	 * 
	 */
	private ReuqestProcessThreadPool() {
		for(int i=0;i<10;i++) {
			ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<>(100);
			RequestQueueHolder.getInstance().addQueue(queue);
			executorService.submit(new RequestProcessThread(queue));
		}
	}
	
	public static ReuqestProcessThreadPool getInstance() {
		return instance;
	}
	
	/**
	 * 初始化的便捷方法
	 */
	public static void init() {
		ReuqestProcessThreadPool.getInstance();
	}
}

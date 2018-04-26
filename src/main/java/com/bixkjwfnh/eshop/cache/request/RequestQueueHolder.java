package com.bixkjwfnh.eshop.cache.request;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 为了加快请求队列的处理速度，将采用多线程的方式来处理， 同时也将请求队列进行划分，使每一个线程都有一个对应的
 * 请求队列，以达到请求队列的快速处理。<br/>
 * 
 * 以requestQueues的变量保存所有请求队列的引用，这样可以很方便的 进行请求队列信息的获取。<br/>
 * 
 * 
 * @since 2018.04.26
 * @author SunQW
 *
 */
public class RequestQueueHolder {

	private static final RequestQueueHolder instance = new RequestQueueHolder();

	/**
	 * 内存队列
	 */
	private List<ArrayBlockingQueue<Request>> requestQueues = new ArrayList<ArrayBlockingQueue<Request>>();

	private RequestQueueHolder() {
	}

	public static RequestQueueHolder getInstance() {
		return instance;
	}

	/**
	 * 添加一个内存队列
	 * 
	 * @param queue
	 */
	public void addQueue(ArrayBlockingQueue<Request> queue) {
		requestQueues.add(queue);
	}

	/**
	 * 获取内存队列的数量
	 * 
	 * @return
	 */
	public Integer queueSize() {
		return requestQueues.size();
	}

	/**
	 * 根据索引获取某个内存队列
	 * 
	 * @param index
	 * @return
	 */
	public ArrayBlockingQueue<Request> getQueue(Integer index) {
		return requestQueues.get(index);
	}
}

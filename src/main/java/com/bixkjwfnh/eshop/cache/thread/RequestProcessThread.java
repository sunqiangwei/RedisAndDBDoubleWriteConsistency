package com.bixkjwfnh.eshop.cache.thread;

import java.util.concurrent.ArrayBlockingQueue;

import com.bixkjwfnh.eshop.cache.request.ProductInventoryCacheRefreshRequest;
import com.bixkjwfnh.eshop.cache.request.ProductInventoryCached;
import com.bixkjwfnh.eshop.cache.request.ProductInventoryDBUpdateRequest;
import com.bixkjwfnh.eshop.cache.request.Request;


/**
 * 执行请求的工作线程
 * 
 * @since 2018.04.26
 * @author SunQW
 *
 */
public class RequestProcessThread implements Runnable {

	/**
	 * 自己需要处理的内存请求队列
	 */
	private ArrayBlockingQueue<Request> queue ;
	
	public RequestProcessThread(ArrayBlockingQueue<Request> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		Request request = null;
		Boolean refresh = null;
		
		try {
			while(true) {
				// ArrayBlockingQueue
				// Blocking就是说明，如果队列满了，或者是空的，那么都会在执行操作的时候，阻塞住
				request = queue.take();
				refresh = true;
				
				if(request instanceof ProductInventoryDBUpdateRequest) {
					// 如果是一个更新数据库的请求，那么就将那个productId对应的标识设置为true
					ProductInventoryCached.getInstance().set(request.getUniqueId(), true);
				}else if(request instanceof ProductInventoryCacheRefreshRequest){
					boolean forceRefresh = ((ProductInventoryCacheRefreshRequest)request).isForceRefresh();
					
					// 先做读请求的去重
					if(!forceRefresh) {
						Boolean flag = ProductInventoryCached.getInstance().get(request.getUniqueId());
							
						// 如果是缓存刷新的请求，那么就判断，如果标识不为空，而且是true，就说明之前有一个这个商品的数据库更新请求
						if(flag != null && flag) {
							ProductInventoryCached.getInstance().set(request.getUniqueId(), false);
						}
							
						// 如果是缓存刷新的请求，而且发现标识不为空，但是标识是false
						// 说明前面已经有一个数据库更新请求+一个缓存刷新请求了，大家想一想
						if(flag != null && !flag) {
							// 对于这种读请求，直接就过滤掉，不要放到后面的内存队列里面去了
							refresh = false;
						}
					}
				}
				
				if(refresh) {
					System.out.println("===========日志===========: 工作线程处理请求，商品id=" + request.getUniqueId()); 
					
					// 执行这个request操作
					request.process();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

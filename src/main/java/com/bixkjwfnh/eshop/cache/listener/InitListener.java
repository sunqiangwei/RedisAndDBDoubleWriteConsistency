package com.bixkjwfnh.eshop.cache.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.bixkjwfnh.eshop.cache.thread.ReuqestProcessThreadPool;

/**
 * 系统启动的时候，进行相关的初始化操作
 * 
 * @since 2018.04.26
 * @author SunQW
 *
 */
@WebListener
public class InitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//初始化内存队列
		ReuqestProcessThreadPool.init();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}

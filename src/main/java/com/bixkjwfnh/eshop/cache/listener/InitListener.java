package com.bixkjwfnh.eshop.cache.listener;

import java.util.EventListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bixkjwfnh.eshop.cache.thread.RequestProcessThread;
import com.bixkjwfnh.eshop.cache.thread.ReuqestProcessThreadPool;

public class InitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ReuqestProcessThreadPool.init();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}

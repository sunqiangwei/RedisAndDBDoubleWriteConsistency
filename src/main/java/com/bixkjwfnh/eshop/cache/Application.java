package com.bixkjwfnh.eshop.cache;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.bixkjwfnh.eshop.cache.listener.InitListener;

@SpringBootApplication
@MapperScan("com.bixkjwfnh.eshop.cache.mapper")
public class Application {
 
	@Bean
	public ServletListenerRegistrationBean servletListenerRegistrationBean(){        
		ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();        
		servletListenerRegistrationBean.setListener(new InitListener());        
		return servletListenerRegistrationBean;    
	}
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}
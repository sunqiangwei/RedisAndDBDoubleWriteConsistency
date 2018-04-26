package com.bixkjwfnh.eshop.cache;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 启动类
 * 
 * @since 2018.04.26
 * @author SunQW
 *
 */
@SpringBootApplication
@EnableCaching
@MapperScan("com.bixkjwfnh.eshop.cache.mapper")
@ServletComponentScan("com.bixkjwfnh.eshop.cache")
public class Application {
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}
package com.shop.cafe;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 
@MapperScan("com.shop.cafe.dao")
public class StockBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockBackendApplication.class, args);
	}

}

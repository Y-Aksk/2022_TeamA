package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.example.demo.LiquorList.repository")
public class LiquorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiquorApplication.class, args);
	}

}

package com.testfbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan(value = "com.testfbs.mappers")
public class TestFbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestFbsApplication.class, args);
    }

}

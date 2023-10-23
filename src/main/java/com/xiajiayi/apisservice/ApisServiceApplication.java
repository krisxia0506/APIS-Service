package com.xiajiayi.apisservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.xiajiayi.apisservice.mapper")
@SpringBootApplication
public class ApisServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApisServiceApplication.class, args);
    }

}

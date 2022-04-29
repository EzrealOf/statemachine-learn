package com.ezreal.squirrel.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ezreal.squirrel")
public class SquirrelEventApplication {

    public static void main(String[] args) {
        SpringApplication.run(SquirrelEventApplication.class, args);
    }
}

package com.cloud.service.lostfound;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.cloud.service.lostfound.domain.mapper")
@SpringBootApplication
public class LostfoundApplication {

    public static void main(String[] args) {
        SpringApplication.run(LostfoundApplication.class, args);
    }

}

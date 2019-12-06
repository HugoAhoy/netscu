package com.netscu.netscu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.netscu.netscu.Mapper")
public class NetscuApplication {
    public static void main(String[] args) {
        SpringApplication.run(NetscuApplication.class, args);
    }

}

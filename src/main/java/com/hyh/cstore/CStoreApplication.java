package com.hyh.cstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan指定Mapper接口的路径，启动项目时自动加载路径下的Mapper接口
@MapperScan("com.hyh.cstore.mapper")
public class CStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CStoreApplication.class, args);
    }

}

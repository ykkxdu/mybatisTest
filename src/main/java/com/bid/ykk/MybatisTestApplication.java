package com.bid.ykk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.bid.ykk.mapper")
public class MybatisTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisTestApplication.class, args);
    }

}

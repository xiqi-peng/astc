package com.astc;

import entity.IdWorker;
import org.mapstruct.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName ShangpinApplication
 * @Description 商品微服务
 * @Author 彭茜奇
 * @Date 16:24 2020/4/12
 * @Version 2.1
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.astc.shangpin.dao")
public class ShangPinApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShangPinApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}

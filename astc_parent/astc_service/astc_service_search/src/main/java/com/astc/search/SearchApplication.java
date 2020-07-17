package com.astc.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @ClassName SearchApplication
 * @Description
 * @Author 彭茜奇
 * @Date 22:40 2020/5/14
 * @Version 2.1
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableElasticsearchRepositories(basePackages = {"com.astc.search.dao"})
@EnableFeignClients(basePackages = {"com.astc.shangpin.feign"})
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }
}

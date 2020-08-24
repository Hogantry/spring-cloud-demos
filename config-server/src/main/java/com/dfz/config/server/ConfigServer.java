package com.dfz.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName ConfigServer
 * @Description ConfigServer
 * @Author dfz
 * @Date 2019-10-30 15:00
 * @Version 1.0
 **/
@SpringBootApplication
@EnableConfigServer
// 推荐使用下面注解来代替@EnableEurekaClient，开启服务发现机制
//@EnableDiscoveryClient
public class ConfigServer {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServer.class, args);
    }

}

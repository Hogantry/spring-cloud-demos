package com.dfz.service.provider;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName Application
 * @Description Application
 * @Author dfz
 * @Date 2019-10-30 11:00
 * @Version 1.0
 **/
@SpringBootApplication
/**
 * 推荐使用@EnableDiscoveryClient来代替@EnableEurekaClient，开启服务发现机制，且@EnableDiscoveryClient注解在在E版本及之后版本
 * 是可选项了，只要依赖了以spring-cloud-starter-netflix为前缀的库(只要classpath中存在DiscoveryClient的实现)，就启用了服务注册发
 * 现功能，使用配置项spring.cloud.service-registry.auto-registration.enabled=false即可禁止服务注册发现功能；
 */
//@EnableDiscoveryClient
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

}

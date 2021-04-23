package com.dfz.service.consumer.feign;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.ClientHttpRequestFactorySupplier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;
//import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
//import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName Application
 * @Description Application
 * @Author dfz
 * @Date 2019-10-30 11:08
 * @Version 1.0
 **/
@SpringBootApplication
// 该注解可以不用加，类路径中存在EurekaClient即可
//@EnableEurekaClient
//@EnableHystrix
//@EnableHystrixDashboard
//@EnableTurbine
//@EnableCircuitBreaker
// TODO 该注解的原理待看，实现的功能与@EnableEurekaClient一致
//@EnableDiscoveryClient
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(RestTemplateBuilder builder, ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate();
        RestTemplateBuilder builder1 = builder.requestFactory(() -> factory);
        return builder1.configure(restTemplate);
    }

    @Bean
    public ClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(5000);
        return factory;
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

}

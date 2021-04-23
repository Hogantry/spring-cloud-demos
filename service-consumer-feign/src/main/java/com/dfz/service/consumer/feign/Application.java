package com.dfz.service.consumer.feign;

import com.dfz.service.consumer.FeignConfiguration;
import com.dfz.service.consumer.feign.feign.HelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @ClassName Application
 * @Description Application
 * @Author dfz
 * @Date 2019-10-30 11:08
 * @Version 1.0
 **/
@SpringBootApplication
/**
 * 推荐使用@EnableDiscoveryClient来代替@EnableEurekaClient，开启服务发现机制，且@EnableDiscoveryClient注解在E版本及之后版本
 * 是可选项了，只要依赖了以spring-cloud-starter-netflix为前缀的库(只要classpath中存在DiscoveryClient的实现)，就启用了服务注册发
 * 现功能，使用配置项spring.cloud.service-registry.auto-registration.enabled=false即可禁止服务注册发现功能；
 */
//@EnableDiscoveryClient
@EnableFeignClients(defaultConfiguration = FeignConfiguration.class)
@RefreshScope
// 推荐使用下面注解来代替@EnableHystrix，开启熔断机制，与feign结合使用，不需要下面注解，与RestTemplete结合使用，需要
// 下面注解来解析@HystrixCommand注解
//@EnableCircuitBreaker
// actuator/refresh
// actuator/bus-refresh
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        HelloService bean = context.getBean(HelloService.class);
        bean.sayHiFromClientOne("dfz");
    }

    //    @Bean
//    public Sampler defaultSampler() {
//        return Sampler.ALWAYS_SAMPLE;
//    }
}

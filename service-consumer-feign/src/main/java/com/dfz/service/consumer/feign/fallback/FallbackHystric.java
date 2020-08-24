package com.dfz.service.consumer.feign.fallback;

import com.dfz.service.consumer.feign.feign.HelloService;
import org.springframework.stereotype.Component;

/**
 * @ClassName FallbackHystric
 * @Description FallbackHystric
 * @Author dfz
 * @Date 2019-10-30 13:40
 * @Version 1.0
 **/
@Component
public class FallbackHystric implements HelloService {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}

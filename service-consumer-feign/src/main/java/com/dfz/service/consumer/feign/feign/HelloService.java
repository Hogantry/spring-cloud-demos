package com.dfz.service.consumer.feign.feign;

import com.dfz.service.consumer.feign.fallback.FallbackHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName HelloService
 * @Description HelloService
 * @Author dfz
 * @Date 2019-10-30 13:27
 * @Version 1.0
 **/
@FeignClient(value = "SERVICE-CONSUMER", fallback = FallbackHystric.class)
public interface HelloService {

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

}

package com.dfz.service.consumer.feign.controller;

import com.dfz.service.consumer.feign.feign.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName HelloController
 * @Description HelloController
 * @Author dfz
 * @Date 2019-10-30 11:08
 * @Version 1.0
 **/
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "DFZ") String name) {
        System.out.println("feign");
        return helloService.sayHiFromClientOne(name);
    }

}

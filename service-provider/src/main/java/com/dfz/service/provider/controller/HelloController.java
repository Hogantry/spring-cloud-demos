package com.dfz.service.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description HelloController
 * @Author dfz
 * @Date 2019-10-30 11:04
 * @Version 1.0
 **/
@RestController
public class HelloController {

    @Value("${server.port}")
    String port;

    @GetMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "DFZ") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }

}

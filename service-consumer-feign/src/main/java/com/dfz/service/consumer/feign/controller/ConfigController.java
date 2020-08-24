package com.dfz.service.consumer.feign.controller;

import com.dfz.service.consumer.feign.properties.GitAutoRefreshConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ConfigController
 * @Description ConfigController
 * @Author dfz
 * @Date 2019-10-30 15:16
 * @Version 1.0
 **/
@RestController
public class ConfigController {

    @Value("${dfz.foo}")
    private String foo;

    @Autowired
    private GitAutoRefreshConfig config;

    @GetMapping(value = "/hello")
    public String hi(){
        return foo;
    }

    @GetMapping(value = "/autoHello")
    public String hello(){
        return config.toString();
    }

}

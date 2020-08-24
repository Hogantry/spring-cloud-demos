package com.dfz.service.consumer.feign.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName GitAutoRefreshConfig
 * @Description GitAutoRefreshConfig
 * @Author dfz
 * @Date 2019-10-31 14:50
 * @Version 1.0
 **/
@Component
@ConfigurationProperties(prefix = "dfz")
public class GitAutoRefreshConfig {

    private String foo;

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    @Override
    public String toString() {
        return "GitAutoRefreshConfig{" +
                "foo='" + foo + '\'' +
                '}';
    }
}

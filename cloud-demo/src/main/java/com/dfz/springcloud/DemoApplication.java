package com.dfz.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @version V1.0
 * @author: DFZ
 * @description: demo
 * @date: 2020/12/31 13:55
 * @Copyright: 2020 www.ztzqzg.com Ltd. All rights reserved.
 * 注意：本内容仅限于中泰证券（上海）资产管理有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@SpringBootApplication
public class DemoApplication {

    @Value("${spring.application.name}")
    private String name;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        String property = context.getEnvironment().getProperty("spring.application.name");
        String property1 = context.getEnvironment().getProperty("spring.application.admin.enabled");
        System.out.println(property1);
        System.out.println(property);
    }

}

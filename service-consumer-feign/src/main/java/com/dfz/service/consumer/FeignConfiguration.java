package com.dfz.service.consumer;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import feign.*;
import feign.hystrix.SetterFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author SoySauce
 * @date 2019/7/30
 */
@Configuration
@ConditionalOnClass({Feign.class})
public class FeignConfiguration {

    private static final String AUTH_HEADER = "Authorization";


    @Value("${${feign.client.name}.token:abc}")
    private String token;


    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    SetterFactory setterFactory() {
        return new SetterFactory() {
            @Override
            public HystrixCommand.Setter create(Target<?> target, Method method) {
                return HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(target.name()));
            }
        };
    }

    @Bean
    public WebMvcRegistrations feignWebRegistrations() {
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return new FeignRequestMappingHandlerMapping();
            }
        };
    }

    private static class FeignRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
        @Override
        protected boolean isHandler(Class<?> beanType) {
            return super.isHandler(beanType) &&
                    !AnnotatedElementUtils.hasAnnotation(beanType, FeignClient.class);
        }
    }

    /**
     * 创建Feign请求拦截器，在发送请求前设置认证的token,各个微服务将token设置到环境变量中来达到通用
     *
     * @return
     */
    @Bean
    public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }

    class FeignBasicAuthRequestInterceptor implements RequestInterceptor {

        public FeignBasicAuthRequestInterceptor() {
        }

        @Override
        public void apply(RequestTemplate template) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            if (attributes == null) {
                return;
            }
            HttpServletRequest request = attributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    String values = request.getHeader(name);
                    template.header(name, values);
                }
            }
            template.header(AUTH_HEADER, token);
            Map<String, Collection<String>> decoded = new LinkedHashMap<>();
            Map<String, String[]> parameterMap = request.getParameterMap();
            for (String key : parameterMap.keySet()) {
                decoded.put(key, Arrays.asList(parameterMap.get(key)));
            }
            Map<String, Collection<String>> queries = template.queries();
            if (!CollectionUtils.isEmpty(queries)) {
                decoded.putAll(queries);
            }
            template.queries(decoded);
        }
    }
}

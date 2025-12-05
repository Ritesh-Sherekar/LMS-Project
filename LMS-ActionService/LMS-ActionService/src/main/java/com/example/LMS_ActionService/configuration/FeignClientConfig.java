package com.example.LMS_ActionService.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.ErrorDecoder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignClientConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (request != null){
            HttpServletRequest request1 = request.getRequest();
            String authorization = request1.getHeader("Authorization");
            System.out.println(authorization);
            if (authorization != null){
                requestTemplate.header("Authorization", authorization);
            }
        }
    }

    @Bean
    public ErrorDecoder errorDecoder(){
        return new QueryServiceErrorDecoder();
    }
}

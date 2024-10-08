package com.webank.wsdaw.gateway.config;

import com.webank.wsdaw.gateway.filter.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = true)
@Slf4j
public class BeanConfig {

    @Autowired private SystemConfig systemConfig;

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> authorizationFilter() {
        FilterRegistrationBean<JwtAuthenticationFilter> registrationBean =
                new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtAuthenticationFilter(systemConfig.getAdminPrivateKey()));
        registrationBean.addUrlPatterns("/*"); // 设置过滤器拦截的 URL 模式
        log.info("JwtAuthenticationFilter registered");
        return registrationBean;
    }
}

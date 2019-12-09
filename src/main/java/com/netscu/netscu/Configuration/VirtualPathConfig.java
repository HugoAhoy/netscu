package com.netscu.netscu.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author HugoAhoy
 * @date 2019/12/9 0009
 * @time 12:21
 */
@Configuration
public class VirtualPathConfig implements WebMvcConfigurer {

    /**
     * 虚拟路径配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:D:\\GitRepository\\NETSCU\\upload\\");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
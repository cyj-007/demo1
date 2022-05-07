package com.example.demo1.common.web.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cyj
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Resource
    private WebProperties webProperties;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        System.out.println(configurer+"web1111");
        // 设置 API 前缀，仅仅匹配 controller 包下的
        configurer.addPathPrefix(webProperties.getApiPrefix(), clazz ->
                clazz.isAnnotationPresent(RestController.class)
                && clazz.getPackage().getName().startsWith(webProperties.getControllerPackage())); // 仅仅匹配 controller 包
    }

    // ========== Filter 相关 ==========

    /**
     * 解决跨域请求
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路由
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许证书（cookies）
                .allowCredentials(true)
                // 设置访问源请求头
                .allowedHeaders("*")
                // 设置允许的方法
                .allowedMethods("*")
                // 跨域允许时间
                .maxAge(3600);
        WebMvcConfigurer.super.addCorsMappings(registry);
    }

    /**
     * 解决@RestController返回json结果时，IE浏览器出现下载json文件的现象。
     * @return MappingJackson2HttpMessageConverter
     */
    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();

        List<MediaType> supportedMediaTypes  = new ArrayList<MediaType>();
        supportedMediaTypes.add(new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8));
        supportedMediaTypes.add(new MediaType(MediaType.TEXT_HTML, StandardCharsets.UTF_8));
        jsonConverter.setSupportedMediaTypes(supportedMediaTypes);
        System.out.println(jsonConverter+"jackson2HttpMessageConverter");
        return jsonConverter;
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jackson2HttpMessageConverter());
    }

}

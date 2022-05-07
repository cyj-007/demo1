package com.example.demo1.common.web.config;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;

/**
 * @author cyj
 */
@Component
@Data
public class WebProperties {

    /**
     * API 前缀，实现所有 Controller 提供的 RESTFul API 的统一前缀
     *
     *
     * 意义：通过该前缀，避免 Swagger、Actuator 意外通过 Nginx 暴露出来给外部，带来安全性问题
     *      这样，Nginx 只需要配置转发到 /api/* 的所有接口即可。
     *
     * @see WebConfiguration#configurePathMatch(PathMatchConfigurer)
     */
    private String apiPrefix = "/api";

    /**
     * Controller 所在包
     *
     * 主要目的是，给该 Controller 设置指定的 {@link #apiPrefix}
     *
     * 因为我们有多个 modules 包里会包含 Controller，所以只需要写到 soft.guide.tajicloud 这样的层级
     */
    private String controllerPackage = "com.example.demo1.modules.controller";

}

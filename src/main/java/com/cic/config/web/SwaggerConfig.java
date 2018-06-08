package com.cic.config.web;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan(basePackages = {"com.cic.module"}) //必须存在 扫描的API Controller package name 也可以直接扫描class (basePackageClasses)
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(initApiInfo());
    }

    private ApiInfo initApiInfo() {
        ApiInfo apiInfo = new ApiInfo("图书小程序接口API", // 大标题
                "", // 简单的描述
                "1.0.0", // 版本
                "服务条款", "钱大仙", // 作者
                "The Apache License, Version 2.0", // 链接显示文字
                "http://www.baidu.com"// 网站链接
        );
        return apiInfo;
    }

}
package com.learn.learnsatoken.config.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

import static com.learn.learnsatoken.config.constant.Constant.SWAGGER_PACKAGE;

/**
 * Swagger配置
 *
 * @author Peter Cheung
 * @since 2023-07-19 11:37:24
 */
@Slf4j
@Configuration
@EnableOpenApi
@EnableKnife4j
public class SwaggerConfig {
    /**
     * 用于读取配置文件 swagger 属性是否开启
     */
    @Value("${swagger.enabled}")
    Boolean swaggerEnabled;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                // 是否开启swagger
                .enable(swaggerEnabled)
                .select()
                // 过滤条件，扫描指定路径下的文件
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_PACKAGE))
                // 指定路径处理，PathSelectors.any()代表不过滤任何路径
                //.paths(PathSelectors.any())
                .build();
    }

    /**
     * 作者信息
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("Peter Cheung", "https://github.com/PerCheung", "13733139823@163.com");
        return new ApiInfo(
                "Swagger3",
                "Swagger3接口文档",
                "v1.0",
                "https://github.com/PerCheung",
                contact,
                "Apache 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }
}


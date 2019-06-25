package com.hurbao.sso.common.datasource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author qzg
 * 访问路径--http://127.0.0.1:8888/sso/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hurbao.sso"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("多数据源 - 测试文档")
                .termsOfServiceUrl("http://www.seawaterbt.com")
                .contact(new Contact("互融宝","https://www.hurbao.com","809318068@qq.com"))
                .version("1.0")
                .build();
    }
}


package tech.paulosalgado.ifoodorder.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("tech.paulosalgado.ifoodorder.infrastructure.web"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData())
                .securitySchemes(Collections.EMPTY_LIST);
    }

    private ApiInfo metaData() {
        return new ApiInfo(
                "iFood: Order", "Collection of Services that simulate iFood's Order parameters.", "1.0.0",
                "https://github.com/paulosalgado/MovileNext3_Backend",
                new Contact("Paulo Salgado", "https://paulosalgado.tech/", "pjosalgado@gmail.com"),
                "MIT", "https://github.com/paulosalgado/MovileNext3_Backend/blob/master/LICENSE",
                Collections.EMPTY_LIST
        );
    }

}

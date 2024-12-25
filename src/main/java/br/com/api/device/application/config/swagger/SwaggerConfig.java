package br.com.api.device.application.config.swagger;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi deviceApi() {
        return GroupedOpenApi.builder()
                .group("Device Management")
                .packagesToScan("br.com.api.device") // Pacote que contém os controladores da API
                .addOpenApiCustomizer(openApi -> openApi.info(new Info()
                        .title("Device Management API")
                        .description("API for managing devices in the system")
                        .version("v1.0")
                        .termsOfService("https://www.api.com/terms-of-service")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Support Team")
                                .url("https://www.api.com/contact")
                                .email("support@api.com"))
                        .license(new io.swagger.v3.oas.models.info.License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))))
                .build();
    }

    @Bean
    public GroupedOpenApi defaultApi() {
        return GroupedOpenApi.builder()
                .group("default")
                .packagesToScan("br.com.api.device") // Pacote que contém os controladores da API
                .addOpenApiCustomizer(openApi -> openApi.info(new Info()
                        .title("Device Management API")
                        .description("API for managing devices in the system")
                        .version("v1.0")
                        .termsOfService("https://www.api.com/terms-of-service")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Support Team")
                                .url("https://www.api.com/contact")
                                .email("support@api.com"))
                        .license(new io.swagger.v3.oas.models.info.License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))))
                .build();
    }
}

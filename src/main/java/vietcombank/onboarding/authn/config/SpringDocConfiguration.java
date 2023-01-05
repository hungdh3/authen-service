package vietcombank.onboarding.authn.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {
    final String securitySchemeName = "bearerAuth";

    @Bean
    OpenAPI apiInfo() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                .info(
                        new Info()
                                .title("User Profile Service - OpenAPI 3.0")
                                .description("API spec for User Profile")
                                .termsOfService("https://vietcombank.com.vn")
                                .contact(
                                        new Contact()
                                                .email("it_dev_support@vietcombank.com.vn")
                                )
                                .license(
                                        new License()
                                                .name("Vietcombank")
                                                .url("https://vietcombank.com.vn")
                                )
                                .version("0.0.1")
                );
    }
}
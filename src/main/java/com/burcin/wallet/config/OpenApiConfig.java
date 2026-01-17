package com.burcin.wallet.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;
@Configuration
public class OpenApiConfig {

    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Wallet API")
                .description("API documentation for the Wallet application")
                .version("1.0.0"));
    }
    
}

package com.ochabdo.security.web.configuration;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;





@Configuration
public class SwaggerConfig {

	//http://localhost:8080/swagger-ui/index.html
   
    @Bean
	public OpenAPI customOpenAPI() {
		
		return new OpenAPI()
				.info(new Info().title("Test JWT Bearer"))				
				.addSecurityItem(new SecurityRequirement().addList("UseSecurityScheme"))
				.components(new Components().addSecuritySchemes("UseSecurityScheme", new SecurityScheme()
						.name("JavaInUseSecurityScheme").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
		
	}

}

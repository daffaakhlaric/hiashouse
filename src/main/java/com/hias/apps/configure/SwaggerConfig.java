package com.hias.apps.configure;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket authApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
			    .select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
//						.basePackage("com.limu.apps.controller"))
				.paths(PathSelectors.any())
		          .build()
		          .apiInfo(apiInfo())
		          .securitySchemes(Arrays.asList(apiKey())
		        		  );
	}

//	private Predicate<String> regex(String param) {
//		return Predicates.and(PathSelectors.regex(param), Predicates.not(PathSelectors.regex("/error.*")));
//	}
	
	// Describe your apis
	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	    .title("Ecommerce Hias Rest APIs")
	    .description("This page lists all the rest apis for Limu Tv.")
	    .version("1.0-SNAPSHOT")
	    .build();
	}
	 private ApiKey apiKey() {
	        return new ApiKey("Bearer", "Authorization", "header");
	      }
	
}

package com.hry.swagger;

import io.swagger.models.Contact;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class SwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerApplication.class, args);
	}


//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.apiInfo(getApiInfo())
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.hry.swagger.ctl"))
//				.paths(PathSelectors.any())
//				.build();
//	}
//
//	private ApiInfo getApiInfo() {
//	//	Contact contact = new Contact("Chandana Napagoda", "http://blog.napagoda.com", "cnapagoda@gmail.com");
//		return new ApiInfoBuilder()
//				.title("Example Api Title")
//				.description("Example Api Definition")
//				.version("1.0.0")
//				.license("Apache 2.0")
//				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
//				.contact("http://blog.napagoda.com")
//				.build();
//	}
}

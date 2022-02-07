package com.zensar;


import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class StockAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockAppApplication.class, args);
	}

@Bean	
public ModelMapper getModelMapper()
{
	return new ModelMapper();
	}

@Bean
public Docket getcustomisedDocket()
{
	return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.zensar"))
			.paths(PathSelectors.any())
			//.paths(PathSelectors.ant("/zenStockApp/*"))
			.build()
			.apiInfo(getApiInfo());
	}

private ApiInfo getApiInfo()
{
ApiInfo apiInfo = new ApiInfo("StockMarket Swagger API",
							"ZENSAR StockMarket swagger API Documentation", 
							"2.0", 
							"http://zensar.com/termsOfServiceURL", 
							new Contact("Kirankumar Shirsath", "95619871657", "kirankumar.shirsath@zensar.com"),
							"GPL",
							"http://zensar.com/license/licenseUrl",
							new ArrayList<VendorExtension>());
							
return apiInfo;
}

}

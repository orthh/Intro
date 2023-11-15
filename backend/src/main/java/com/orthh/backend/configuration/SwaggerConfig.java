package com.orthh.backend.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {
  @Bean
  // localhost:xxxx/swagger-ui/index.html/ << 접속경로
  public OpenAPI baseOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("orthh's API").version("0.0.1").description("개인기록 페이지"));
  }

  @Bean
  public GroupedOpenApi group1() {
    String[] packagesToScan = {"com.orthh.backend.admin"};

    return GroupedOpenApi.builder().group("admin").packagesToScan(packagesToScan).build();
  }

  @Bean
  public GroupedOpenApi group2() {
    String[] pathsToMatch = {"/user/**"};
    // String[] pathsToExclued = {""};

    return GroupedOpenApi.builder().group("user").pathsToMatch(pathsToMatch).build();
  }

  @Bean
  public GroupedOpenApi group3() {
    String[] pathsToMatch = {"/v1/**"};
    // String[] pathsToExclued = {""};

    return GroupedOpenApi.builder().group("jwt").pathsToMatch(pathsToMatch).build();
  }
}

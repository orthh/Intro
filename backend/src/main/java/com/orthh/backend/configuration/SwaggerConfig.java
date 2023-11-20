package com.orthh.backend.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger 설정파일
 *
 * @author 김혁
 * @since 2023.11.13
 * @version 1.0
 */
@Configuration
@OpenAPIDefinition
public class SwaggerConfig {
  @Bean
  // localhost:xxxx/swagger-ui/index.html/ << 접속경로
  public OpenAPI baseOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("orthh's API")
                .version("0.0.1")
                .description("물품대여 시스템 (인증이 필요한부분은 막혀있습니다. 추후 swagger 적용예정)"));
  }

  // @Bean
  // public GroupedOpenApi group1() {
  //   String[] packagesToScan = {"com.orthh.backend.admin"};

  //   return GroupedOpenApi.builder().group("admin").packagesToScan(packagesToScan).build();
  // }

  // @Bean
  // public GroupedOpenApi group2() {
  //   String[] pathsToMatch = {"/user/**"};
  //   // String[] pathsToExclued = {""};

  //   return GroupedOpenApi.builder().group("user").pathsToMatch(pathsToMatch).build();
  // }

  @Bean
  public GroupedOpenApi group3() {
    String[] pathsToMatch = {"/v1/**"};
    // String[] pathsToExclued = {""};

    return GroupedOpenApi.builder().group("jwt").pathsToMatch(pathsToMatch).build();
  }

  @Bean
  public GroupedOpenApi group4() {
    String[] pathsToMatch = {"/product/**"};
    // String[] pathsToExclued = {""};

    return GroupedOpenApi.builder().group("product").pathsToMatch(pathsToMatch).build();
  }

  @Bean
  public GroupedOpenApi group5() {
    String[] pathsToMatch = {"/rental/**"};
    // String[] pathsToExclued = {""};

    return GroupedOpenApi.builder().group("rental").pathsToMatch(pathsToMatch).build();
  }
}

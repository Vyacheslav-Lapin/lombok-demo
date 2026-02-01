package ru.vlapin.demo.lombokdemo;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(properties = {
    "spring.docker.compose.enabled=false",
    "management.metrics.export.defaults.enabled=false",
    "management.observations.enabled=false",
    // Отключаем проблемную часть springdoc, которая тянет Data REST/HATEOAS(HAL) и валит контекст на Boot 4.x
    "spring.autoconfigure.exclude=" +
        "org.springdoc.core.configuration.SpringDocDataRestConfiguration," +
        "org.springdoc.core.configuration.SpringDocHateoasConfiguration"
})
@AutoConfigureMockMvc
@Testcontainers(disabledWithoutDocker = true)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class CatTest {

  @Container
  @ServiceConnection
  @SuppressWarnings("unused")
  static PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>("postgres:latest");

  MockMvcTester mockMvcTester;

  @Test
  @SneakyThrows
  @DisplayName("Cats is accessible via REST")
  void catsIsAccessibleViaRestTest() {
    mockMvcTester.get()
                 .uri("/cats")
                 .with(user("admin").roles("ADMIN"))
                 .assertThat()
                 .hasStatusOk()
                 .hasContentType("application/vnd.hal+json")
                 .bodyJson()
                 .hasPathSatisfying("$.page.totalElements", provider ->
                     provider.assertThat().convertTo(int.class).isEqualTo(3))
                 .hasPathSatisfying("$.page.totalElements", provider ->
                     provider.assertThat().convertTo(int.class).isEqualTo(3));
  }
}

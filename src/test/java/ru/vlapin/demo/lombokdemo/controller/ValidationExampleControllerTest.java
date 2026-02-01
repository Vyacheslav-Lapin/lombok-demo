package ru.vlapin.demo.lombokdemo.controller;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@SpringBootTest(properties = {
    "spring.docker.compose.enabled=false",
    // Отключаем проблемную часть springdoc, которая тянет Data REST/HATEOAS(HAL) и валит контекст на Boot 4.x
    "spring.autoconfigure.exclude=" +
        "org.springdoc.core.configuration.SpringDocDataRestConfiguration," +
        "org.springdoc.core.configuration.SpringDocHateoasConfiguration"
})
@AutoConfigureMockMvc
//@WithMockUser(authorities = "ADMIN")
@Testcontainers(disabledWithoutDocker = true)
@DisplayNameGeneration(ReplaceCamelCase.class)
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ValidationExampleControllerTest {

  @Container
  @ServiceConnection
  @SuppressWarnings("unused")
  static PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>("postgres:latest");

  @Autowired
  MockMvc mockMvc;

  @Test
  @Disabled
  @SneakyThrows
  void validCardNumber() {
    mockMvc.perform(MockMvcRequestBuilders.post("/card")
                                          .param("cardNumber", "1111-1111-1111-1111")
           )
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.phoneNumbers[:1].type").isNumber())
           .andDo(print());
  }

  @Test
  @Disabled
  @SneakyThrows
  void invalidCardNumber() {
    mockMvc.perform(MockMvcRequestBuilders.post("/card")
                                          .param("cardNumber", "1111-1111-1111"))
           .andExpect(status().is(400))
           .andDo(print());
  }
}

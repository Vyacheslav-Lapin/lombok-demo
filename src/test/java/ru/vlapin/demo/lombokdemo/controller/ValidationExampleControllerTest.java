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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
//@WithMockUser(authorities = "ADMIN")
@DisplayNameGeneration(ReplaceCamelCase.class)
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ValidationExampleControllerTest {

  @Container
  @ServiceConnection
  @SuppressWarnings("unused")
  static PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>("postgres:latest");

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

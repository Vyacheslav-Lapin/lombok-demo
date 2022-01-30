package ru.vlapin.demo.lombokdemo;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.hateoas.MediaTypes.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(authorities = "ADMIN")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class CatTest {

  MockMvc mockMvc;

  @Test
  @SneakyThrows
  @DisplayName("Cats is accessible via REST")
  void catsIsAccessibleViaRestTest() {
    mockMvc.perform(get("/cats"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(HAL_JSON_VALUE))
        .andExpect(jsonPath("$.page.totalElements").isNumber())
        .andExpect(jsonPath("$.page.totalElements").value(3));
  }
}

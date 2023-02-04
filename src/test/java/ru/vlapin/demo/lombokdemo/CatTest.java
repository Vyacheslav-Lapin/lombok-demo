package ru.vlapin.demo.lombokdemo;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
    mockMvc.perform(MockMvcRequestBuilders.get("/cats"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaTypes.HAL_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.jsonPath("$.page.totalElements").isNumber())
        .andExpect(MockMvcResultMatchers.jsonPath("$.page.totalElements").value(3));
  }
}

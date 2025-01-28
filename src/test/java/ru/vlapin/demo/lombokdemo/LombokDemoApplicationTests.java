package ru.vlapin.demo.lombokdemo;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.vlapin.demo.lombokdemo.common.TestUtils;

@Slf4j
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@SuppressWarnings("java:S125")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@ExtensionMethod(value = TestUtils.class, suppressBaseMethods = false)
class LombokDemoApplicationTests {

  @Container
  @ServiceConnection
  @SuppressWarnings({"unused", "java:S3008"})
  static PostgreSQLContainer<?> __ = new PostgreSQLContainer<>("postgres:latest");

  MockMvcTester mockMvcTester;

  @Value("${springdoc.api-docs.path:/v3/api-docs}")
  String apiDocsPath;

  @Test
  @SuppressWarnings({"java:S2699", "java:S1135"})
  void contextLoads() {
    // this method is empty because it tests Spring application context load
  }

  @Test
  @SneakyThrows
  @DisplayName("Openapi documentation generated correctly")
  void openapiDocumentationGeneratedCorrectlyTest() {
    // given
    mockMvcTester.get().uri(apiDocsPath + ".yaml").assertThat()
                 .hasStatusOk()
                 .hasContentType("application/vnd.oai.openapi")
                 .bodyText()
//                 .matches(s -> {
//                   log.info("contentAsString: {}", s);
//                   return true;
//                 })
                 .matches(s -> {
                   "openapi.yaml".toGeneratedSource(s);
                   return true;
                 });
//    val contentAsString = mockMvc.perform(MockMvcRequestBuilders.get(apiDocsPath + ".yaml"))
//                                 .andExpect(MockMvcResultMatchers.status().isOk())
//                                 .andExpect(MockMvcResultMatchers.content().contentType(MediaTypes.HAL_JSON_VALUE))
//                                 .andExpect(MockMvcResultMatchers.jsonPath("$.page.totalElements").isNumber())
//                                 .andExpect(MockMvcResultMatchers.jsonPath("$.page.totalElements").value(3))
//                                 .andReturn().getResponse().getContentAsString();

//    log.info("contentAsString: {}", contentAsString);
  }
}

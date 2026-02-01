package ru.vlapin.demo.lombokdemo;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.vlapin.demo.lombokdemo.common.TestUtils;

@Slf4j
@AutoConfigureMockMvc
@SuppressWarnings("java:S125")
@Testcontainers(disabledWithoutDocker = true)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringBootTest(properties = {
    "spring.docker.compose.enabled=false",
    // Отключаем проблемную часть springdoc, которая тянет Data REST/HATEOAS(HAL) и валит контекст на Boot 4.x
    "spring.autoconfigure.exclude=" +
        "org.springdoc.core.configuration.SpringDocDataRestConfiguration," +
        "org.springdoc.core.configuration.SpringDocHateoasConfiguration"
})
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
    // this method is empty because it tests a Spring application context load
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

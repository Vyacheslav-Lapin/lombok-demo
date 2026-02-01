package ru.vlapin.demo.lombokdemo;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import ru.vlapin.demo.lombokdemo.common.TestUtils;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@Slf4j
@AutoConfigureMockMvc
@SuppressWarnings("java:S125")
@SpringBootTest(properties = {
    // Отключаем проблемную часть springdoc, которая тянет Data REST/HATEOAS(HAL) и валит контекст на Boot 4.x
    "spring.autoconfigure.exclude=" +
        "org.springdoc.core.configuration.SpringDocDataRestConfiguration," +
        "org.springdoc.core.configuration.SpringDocHateoasConfiguration"
})
@DisplayNameGeneration(ReplaceCamelCase.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@ExtensionMethod(value = TestUtils.class, suppressBaseMethods = false)
class LombokDemoApplicationTests {

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

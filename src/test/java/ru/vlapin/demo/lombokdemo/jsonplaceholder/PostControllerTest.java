package ru.vlapin.demo.lombokdemo.jsonplaceholder;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.InstanceOfAssertFactories.*;
import static ru.vlapin.demo.lombokdemo.jsonplaceholder.client.model.PostAssert.assertThat;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.api.PostApiClient;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.model.Post;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringBootTest
@DisplayNameGeneration(ReplaceCamelCase.class)
class PostControllerTest {

  private static final int ID = 57;
  private static final int USER_ID = 6;
  private static final String TITLE = "sed ab est est";
  private static final String BODY = """
          at pariatur consequuntur earum quidem
          quo est laudantium soluta voluptatem
          qui ullam et est
          et cum voluptas voluptatum repellat est""";

  PostApiClient postsApiClient;

  @Test
  @Tag("integration")
  @DisplayName("Get method works correctly")
  void get() {
    Assertions.assertThat(postsApiClient.posts(null)).isNotNull()
              .extracting(HttpEntity::getBody, as(list(Post.class)))
              .isNotEmpty()
              .hasSize(100);
  }

  @Test
  @Tag("integration")
  @DisplayName("Get one post method works correctly")
  void getOnePostMethodWorksCorrectlyTest() {
    assertThat(postsApiClient.pickPost(ID).getBody()).isNotNull()
            .hasId(ID)
            .hasTitle(TITLE)
            .hasUserId(USER_ID)
            .hasBody(BODY);
  }
}

package ru.vlapin.demo.lombokdemo.jsonplaceholder;

import lombok.SneakyThrows;
import org.jetbrains.annotations.Contract;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.service.jsonplaceholder.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PostControllerTest {

  private final long id = 57L;
  private final PostService postService;

  @Autowired
  @Contract(pure = true)
  public PostControllerTest(PostService postService) {
    this.postService = postService;
  }

  @Test
  @SneakyThrows
  @DisplayName("Get method works correctly")
  void get() {
    assertThat(postService.all()).isNotNull()
        .isNotEmpty()
        .hasSize(100);
  }

  @Test
  @SneakyThrows
  @DisplayName("Get one post method works correctly")
  void getOnePostMethodWorksCorrectlyTest() {
    assertThat(postService.findById(id)).isNotNull()
        .matches(post -> post.getId() == id, "id is equals")
        .matches(post -> post.getBody().equals("""
            at pariatur consequuntur earum quidem
            quo est laudantium soluta voluptatem
            qui ullam et est
            et cum voluptas voluptatum repellat est"""), "body is equals");
  }
}

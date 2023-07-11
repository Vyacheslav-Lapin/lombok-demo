package ru.vlapin.demo.lombokdemo.controller.jsonplaceholder;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.api.PostApiClient;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.model.Post;

import java.util.List;
import java.util.Objects;

@ExtensionMethod({
        Objects.class,
})
@RestController
@RequiredArgsConstructor
@RequestMapping("api/posts")
@SuppressWarnings({"java:S2259", "java:S125"})
public class PostController {

  PostApiClient client;

  @GetMapping
  public List<Post> get() {
    return client.posts(null)
            .getBody()
            .requireNonNull();
  }

  @GetMapping("{id}")
  public Post get(@PathVariable Integer id) {
    return client.pickPost(id)
            .getBody()
            .requireNonNull();
  }

  //  @GetMapping
  //  @Contract(pure = true)
  //  public List<Post> postsByUser(@RequestParam Long userId) {
  //    return client.postsByUserId(userId);
  //  }

  //  @Contract(pure = true)
  //  @GetMapping("{id}/comments")
  //  public List<Comment> commentsByPostId(@PathVariable Long id) {
  //    return postService.commentsByPostId(id);
  //  }
}

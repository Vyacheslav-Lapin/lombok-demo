package ru.vlapin.demo.lombokdemo.controller.jsonplaceholder;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
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
@SuppressWarnings("java:S125")
@RequestMapping("api/posts")
public class PostController {

  PostApiClient client;

  @NotNull
  @GetMapping
  @Contract(pure = true)
  public List<Post> get() {
    return client.posts(null)
            .getBody()
            .requireNonNull();
  }

  @NotNull
  @GetMapping("{id}")
  public Post get(@PathVariable @NotNull Integer id) {
    return client.pickPost(id)
            .getBody()
            .requireNonNull();
  }

  //  @NotNull
  //  @GetMapping
  //  @Contract(pure = true)
  //  public List<Post> postsByUser(@RequestParam @NotNull Long userId) {
  //    return client.postsByUserId(userId);
  //  }

  //  @NotNull
  //  @Contract(pure = true)
  //  @GetMapping("{id}/comments")
  //  public List<Comment> commentsByPostId(@PathVariable @NotNull Long id) {
  //    return postService.commentsByPostId(id);
  //  }
}

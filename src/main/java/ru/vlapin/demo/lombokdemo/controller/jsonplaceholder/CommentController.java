package ru.vlapin.demo.lombokdemo.controller.jsonplaceholder;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.api.CommentApiClient;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.model.Comment;

import java.util.List;
import java.util.Objects;

@ExtensionMethod({
        Objects.class,
})
@RestController
@RequiredArgsConstructor
@SuppressWarnings("java:S2259")
@RequestMapping("api/comments")
public class CommentController {

  CommentApiClient client;

  @NotNull
  @GetMapping
  public List<Comment> comments() {
    return client.comments(null)
            .getBody()
            .requireNonNull();
  }

  @NotNull
  @GetMapping("{id}")
  @SuppressWarnings("java:S125")
  public Comment comment(@PathVariable @NotNull Integer id) {
    return client.pickComment(id)
            .getBody()
            .requireNonNull();
  }

  //  @NotNull
  //  @GetMapping
  //  @Contract(pure = true)
  //  public List<Comment> commentsByPostId(@RequestParam @NotNull Long postId) {
  //    return client.commentsByPostId(postId);
  //  }
}

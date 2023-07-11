package ru.vlapin.demo.lombokdemo.controller.jsonplaceholder;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
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
@SuppressWarnings({"java:S2259", "java:S125"})
@RequestMapping("api/comments")
public class CommentController {

  CommentApiClient client;

  @GetMapping
  public List<Comment> comments() {
    return client.comments(null) //todo 12.05.2023: generate 0-params method for http-methods with optional params
            .getBody()
            .requireNonNull();
  }

  @GetMapping("{id}")
  public Comment comment(@PathVariable Integer id) {
    return client.pickComment(id)
            .getBody()
            .requireNonNull();
  }

  //  @GetMapping
  //  @Contract(pure = true)
  //  public List<Comment> commentsByPostId(@RequestParam Long postId) {
  //    return client.commentsByPostId(postId);
  //  }
}

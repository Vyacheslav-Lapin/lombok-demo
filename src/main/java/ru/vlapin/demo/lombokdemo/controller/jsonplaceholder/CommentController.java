package ru.vlapin.demo.lombokdemo.controller.jsonplaceholder;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlapin.demo.lombokdemo.model.jsonplaceholder.Comment;
import ru.vlapin.demo.lombokdemo.service.jsonplaceholder.CommentService;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("java:S125")
@RequestMapping("api/comments")
public class CommentController {

  CommentService commentService;

  @NotNull
  @GetMapping
  @Contract(pure = true)
  public List<Comment> comments() {
    return commentService.all();
  }

  @NotNull
  @GetMapping("{id}")
  @Contract(pure = true)
  @SuppressWarnings("java:S125")
  public Comment comment(@PathVariable @NotNull Long id) {
    return commentService.findById(id);
  }

  //  @NotNull
  //  @GetMapping
  //  @Contract(pure = true)
  //  public List<Comment> commentsByPostId(@RequestParam @NotNull Long postId) {
  //    return client.commentsByPostId(postId);
  //  }
}

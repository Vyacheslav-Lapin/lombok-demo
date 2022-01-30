package ru.vlapin.demo.lombokdemo.service.jsonplaceholder;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vlapin.demo.lombokdemo.model.jsonplaceholder.Comment;

@FeignClient(
    name = "CommentJsonPlaceHolder",
    url = "https://jsonplaceholder.typicode.com",
    path = "comments")
public interface CommentService {

  @GetMapping
  List<Comment> all();

  @GetMapping("{id}")
  Comment findById(@PathVariable Long id);

  @GetMapping("posts/{postId}/comments")
  List<Comment> commentsByPostId(@PathVariable @NotNull Long postId);
}

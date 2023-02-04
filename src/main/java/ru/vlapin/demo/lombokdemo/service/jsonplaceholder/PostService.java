package ru.vlapin.demo.lombokdemo.service.jsonplaceholder;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vlapin.demo.lombokdemo.model.jsonplaceholder.Post;

@FeignClient(name = "PostJsonPlaceHolder",
             url = "https://jsonplaceholder.typicode.com",
             path = "posts")
public interface PostService {

  @GetMapping
  List<Post> all();

  @GetMapping("{id}")
  Post findById(@PathVariable Long id);

  @GetMapping
  List<Post> findByUserId(@RequestParam @NotNull Long userId);
}

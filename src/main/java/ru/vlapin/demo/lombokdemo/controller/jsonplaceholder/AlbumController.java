package ru.vlapin.demo.lombokdemo.controller.jsonplaceholder;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlapin.demo.lombokdemo.model.jsonplaceholder.Album;
import ru.vlapin.demo.lombokdemo.service.jsonplaceholder.AlbumService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/albums")
public class AlbumController {

  AlbumService albumService;

  @NotNull
  @GetMapping
  @Contract(pure = true)
  public List<Album> get() {
    return albumService.all();
  }

  @NotNull
  @GetMapping("{id}")
  @Contract(pure = true)
  public Album get(@PathVariable @NotNull Long id) {
    return albumService.findById(id);
  }

//  @NotNull
//  @GetMapping
//  @Contract(pure = true)
//  public List<AlbumImpl> getByPostId(@RequestParam @NotNull Long postId) {
//    return client.albumsByPostId(postId);
//  }
}

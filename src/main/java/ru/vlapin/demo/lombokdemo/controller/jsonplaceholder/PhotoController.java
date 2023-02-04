package ru.vlapin.demo.lombokdemo.controller.jsonplaceholder;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlapin.demo.lombokdemo.model.jsonplaceholder.Photo;
import ru.vlapin.demo.lombokdemo.service.jsonplaceholder.PhotoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/photo")
@SuppressWarnings("java:S125")
public class PhotoController {

  PhotoService photoService;

  @NotNull
  @GetMapping
  @Contract(pure = true)
  public List<Photo> get() {
    return photoService.all();
  }

  @NotNull
  @GetMapping("{id}")
  @Contract(pure = true)
  public Photo get(@PathVariable @NotNull Long id) {
    return photoService.findById(id);
  }

  //  @NotNull
  //  @GetMapping
  //  @Contract(pure = true)
  //  public List<AlbumImpl> getByPostId(@RequestParam @NotNull Long postId) {
  //    return client.albumsByPostId(postId);
  //  }
}

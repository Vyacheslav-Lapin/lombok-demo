package ru.vlapin.demo.lombokdemo.controller.jsonplaceholder;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.api.AlbumApiClient;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.model.Album;

import java.util.List;
import java.util.Objects;

@ExtensionMethod({
        Objects.class,
})
@Slf4j
@RestController
@RequiredArgsConstructor
@SuppressWarnings("java:S125")
@RequestMapping("api/albums")
public class AlbumController {

  AlbumApiClient client;

  @NotNull
  @GetMapping
  @Contract(pure = true)
  public List<Album> get() {
    return client.albums(null)
            .getBody()
            .requireNonNull();
  }

  @NotNull
  @Contract(pure = true)
  @GetMapping(path = "{id}",
          produces = "application/json")
  public Album get(@PathVariable @NotNull Integer id) {
    return client.pickAlbum(id)
            .getBody()
            .requireNonNull();
  }

  //  @NotNull
  //  @GetMapping
  //  @Contract(pure = true)
  //  public List<AlbumImpl> getByPostId(@RequestParam @NotNull Long postId) {
  //    return client.albumsByPostId(postId);
  //  }
}

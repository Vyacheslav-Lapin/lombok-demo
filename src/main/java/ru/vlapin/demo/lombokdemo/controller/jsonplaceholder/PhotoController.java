package ru.vlapin.demo.lombokdemo.controller.jsonplaceholder;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.api.PhotoApiClient;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.model.Photo;

import java.util.List;
import java.util.Objects;

@ExtensionMethod({
        Objects.class,
})
@RestController
@RequiredArgsConstructor
@RequestMapping("api/photo")
@SuppressWarnings({"java:S2259", "java:S125"})
public class PhotoController {

  PhotoApiClient client;

  @GetMapping
  public List<Photo> get() {
    return client.photos(null)
            .getBody()
            .requireNonNull();
  }

  @GetMapping("{id}")
  public Photo get(@PathVariable Integer id) {
    return client.pickPhoto(id)
            .getBody()
            .requireNonNull();
  }

  //  @GetMapping
  //  @Contract(pure = true)
  //  public List<AlbumImpl> getByPostId(@RequestParam Long postId) {
  //    return client.albumsByPostId(postId);
  //  }
}

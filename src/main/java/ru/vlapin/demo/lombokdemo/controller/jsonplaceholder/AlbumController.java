package ru.vlapin.demo.lombokdemo.controller.jsonplaceholder;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.api.AlbumApiClient;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.model.Album;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.*;

@ExtensionMethod({
        Objects.class,
})
@Slf4j
@RestController
@RequiredArgsConstructor
@SuppressWarnings("java:S2259")
@RequestMapping("api/albums")
public class AlbumController {

  AlbumApiClient client;

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public List<Album> get() {
    return client.albums(null)
            .getBody()
            .requireNonNull();
  }

  @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
  public Album get(@PathVariable Integer id) {
    return client.pickAlbum(id)
            .getBody()
            .requireNonNull();
  }
}

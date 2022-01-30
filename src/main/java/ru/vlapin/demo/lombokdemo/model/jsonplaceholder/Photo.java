package ru.vlapin.demo.lombokdemo.model.jsonplaceholder;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder(toBuilder = true)
public class Photo {

  Long id;

  Long albumId;

  String title;

  String url;

  String thumbnailUrl;
}

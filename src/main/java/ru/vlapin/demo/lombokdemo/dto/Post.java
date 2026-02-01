package ru.vlapin.demo.lombokdemo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
//import lombok.extern.jackson.Jacksonized;
//import ru.vlapin.demo.lombokdemo.dto.Post.PostBuilder;


/**
 * Пример того, как необходимо оформлять DTO в условиях, когда <a href="https://github.com/projectlombok/lombok/issues/3950">issue</a> ещё
 * не по-fix'ено.
 */
@Value
//@Jacksonized
@Builder(toBuilder = true)
@JsonDeserialize(builder = Post.PostBuilder.class)
public class Post {

  Integer id;
  Integer userId;
  String title;
  String body;

  @JsonPOJOBuilder(withPrefix = "")
  public static class PostBuilder {
  }
}

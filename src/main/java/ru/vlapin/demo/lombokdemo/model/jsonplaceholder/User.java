package ru.vlapin.demo.lombokdemo.model.jsonplaceholder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder(toBuilder = true)
public class User {

  Long id;

  String name;

  @JsonProperty("username")
  String userName;

  String email;

  Address address;

  String phone;

  @JsonProperty("website")
  String webSite;

  Company company;
}

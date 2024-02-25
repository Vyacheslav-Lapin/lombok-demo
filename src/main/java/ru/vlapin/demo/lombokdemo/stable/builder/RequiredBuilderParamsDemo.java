package ru.vlapin.demo.lombokdemo.stable.builder;

import static lombok.AccessLevel.*;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(makeFinal = true)
@AllArgsConstructor(access = PRIVATE)
public class RequiredBuilderParamsDemo {
  @NonNull String name; // Presumably name cannot be null since its required by the builder
  @Default String surname = "Pupkin";
  LocalDate dob;

  public static RequiredBuilderParamsDemoBuilder builder(String name) {
    return new RequiredBuilderParamsDemoBuilder().name(name);
  }
}

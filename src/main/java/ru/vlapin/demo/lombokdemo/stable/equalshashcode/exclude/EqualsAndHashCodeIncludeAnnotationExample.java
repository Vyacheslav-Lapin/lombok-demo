package ru.vlapin.demo.lombokdemo.stable.equalshashcode.exclude;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.NonFinal;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EqualsAndHashCodeIncludeAnnotationExample {

  @NonFinal private Long id;

  @EqualsAndHashCode.Include
  @NonFinal private String firstName;

  @EqualsAndHashCode.Include
  @NonFinal private String lastName;

  @NonFinal private LocalDate dob;
}

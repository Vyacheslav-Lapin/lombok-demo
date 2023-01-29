package ru.vlapin.demo.lombokdemo.stable.equalshashcode.exclude;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.NonFinal;

@Data
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "dob"})
public class EqualsAndHashCodeExcludeAnnotationParamExample {

  @NonFinal private Long id;
  @NonFinal private String firstName;
  @NonFinal private String lastName;
  @NonFinal private LocalDate dob;
}

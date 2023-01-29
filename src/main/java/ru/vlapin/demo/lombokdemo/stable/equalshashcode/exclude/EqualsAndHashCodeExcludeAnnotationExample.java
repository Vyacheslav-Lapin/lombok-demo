package ru.vlapin.demo.lombokdemo.stable.equalshashcode.exclude;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.NonFinal;

@Data
//@EqualsAndHashCode // don't need - included in @Data annotstion
@AllArgsConstructor
public class EqualsAndHashCodeExcludeAnnotationExample {

  @EqualsAndHashCode.Exclude
  @NonFinal
  private Long id;

  @NonFinal
  private String firstName;
  @NonFinal
  private String lastName;

  @EqualsAndHashCode.Exclude
  @NonFinal
  private LocalDate dob;
}

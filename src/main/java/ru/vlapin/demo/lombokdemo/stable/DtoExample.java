package ru.vlapin.demo.lombokdemo.stable;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.NonFinal;

/**
 * A data transfer object (DTO) designed to hold specific data fields.
 * This class uses Lombok annotations for boilerplate code reductions such as
 * getter and setter methods and provides fluent-style method chaining.
 * <p>
 * Annotations:
 * - {@code @Getter} generates getter methods for fields.
 * - {@code @Setter} generates setter methods for fields.
 * - {@code @NoArgsConstructor} generates a no-argument constructor.
 * - {@code @Accessors(fluent = true)} configures Lombok to generate fluent accessors.
 * <p>
 * Fields:
 * - {@code x}: Represents a {@link Date} field.
 * - {@code y}: Represents an integer field.
 */
//@Value
//@WithBy
@Getter
@Setter
@NoArgsConstructor
@Accessors(fluent = true)
public class DtoExample {
  @NonFinal Date x;
  @NonFinal int y;
}

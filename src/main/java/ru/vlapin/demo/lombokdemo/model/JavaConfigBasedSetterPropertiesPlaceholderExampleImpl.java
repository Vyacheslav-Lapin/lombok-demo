package ru.vlapin.demo.lombokdemo.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * Features:
 * - default values (Convention over Configuration)
 * - code-completion by IDEA in *.properties and *.yaml files
 * - javadoc-comments as a hints in code-completion by IDEA
 *
 * Limitations:
 * - need for no-args-constructor
 * - need for PUBLIC setters
 *   - so, you have to extract other methods to separate interface and use it by reference
 */
@Data
@Validated
public class JavaConfigBasedSetterPropertiesPlaceholderExampleImpl
    implements JavaConfigBasedSetterPropertiesPlaceholderExample {

  /**
   * My hostname
   */
  @NotBlank String host = "localhost";

  /**
   * My port
   */
  @Min(1_000)
  @Max(65_535)
  Integer port = 8080;
}

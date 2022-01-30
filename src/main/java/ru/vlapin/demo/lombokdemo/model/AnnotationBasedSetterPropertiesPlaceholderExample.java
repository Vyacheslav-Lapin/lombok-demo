package ru.vlapin.demo.lombokdemo.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@ConfigurationProperties("my-properties")
public class AnnotationBasedSetterPropertiesPlaceholderExample {

  /**
   * My hostname
   */
  @NotBlank
  String host = "localhost";

  /**
   * My port
   */
  @Min(1_000)
  @Max(65_535)
  Integer port = 8080;
}

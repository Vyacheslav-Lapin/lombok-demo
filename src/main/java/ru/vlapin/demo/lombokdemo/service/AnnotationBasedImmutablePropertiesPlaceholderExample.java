package ru.vlapin.demo.lombokdemo.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

public sealed interface AnnotationBasedImmutablePropertiesPlaceholderExample {

  String getAuthMethod();

  String getUsername();

  String getPassword();
}

/**
 * Limitations:
 * - no defaults (!!!)
 */
@Getter
@ConfigurationProperties("mail.credentials")
@RequiredArgsConstructor(onConstructor_ = @ConstructorBinding)
final class AnnotationBasedImmutablePropertiesPlaceholderExampleImpl
    implements AnnotationBasedImmutablePropertiesPlaceholderExample {

  /**
   * Auth method
   */
  //@DefaultValue("Lorem ipsum dolor sit amet")
  String authMethod;

  /**
   * login
   */
  String username;

  /**
   * pwd
   */
  String password;
}

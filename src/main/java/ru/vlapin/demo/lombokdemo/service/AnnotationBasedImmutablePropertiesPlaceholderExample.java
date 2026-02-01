package ru.vlapin.demo.lombokdemo.service;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

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
//@RequiredArgsConstructor(onConstructor_ = @ConstructorBinding)
final class AnnotationBasedImmutablePropertiesPlaceholderExampleImpl
    implements AnnotationBasedImmutablePropertiesPlaceholderExample {

  /**
   * Auth method
   */

  String authMethod;

  /**
   * login
   */
  String username;

  /**
   * pwd
   */
  String password;

  @ConstructorBinding
  public AnnotationBasedImmutablePropertiesPlaceholderExampleImpl(
      @DefaultValue("Lorem ipsum dolor sit amet") String authMethod,
      String username,
      String password) {
    this.authMethod = authMethod;
    this.username = username;
    this.password = password;
  }
}

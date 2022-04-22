package ru.vlapin.demo.lombokdemo.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

public interface AnnotationBasedImmutablePropertiesPlaceholderExample {

  String getAuthMethod();

  String getUsername();

  String getPassword();
}

/**
 * Limitations:
 * - no defaults (!!!)
 */
@Getter
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties("mail.credentials")
class AnnotationBasedImmutablePropertiesPlaceholderExampleImpl
    implements AnnotationBasedImmutablePropertiesPlaceholderExample {

  /**
   * Auth method
   */
//  @DefaultValue("Lorem ipsum dolor sit amet")
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

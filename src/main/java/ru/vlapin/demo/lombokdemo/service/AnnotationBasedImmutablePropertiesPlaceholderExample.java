package ru.vlapin.demo.lombokdemo.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * Limitations:
 * - no defaults (!!!)
 */
@Getter
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties("mail.credentials")
public final class AnnotationBasedImmutablePropertiesPlaceholderExample {

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

}

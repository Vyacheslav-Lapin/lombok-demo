package ru.vlapin.demo.lombokdemo.stable.data.meta;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * AutowiredConstructor.
 */
@Target(TYPE)
@Retention(SOURCE)
//@AllArgsConstructor(onConstructor = @ _(@Autowired))
public @interface AutowiredConstructor {
}

@SuppressWarnings({"unused", "NotNullFieldNotInitialized"})
//@AllArgsConstructor(onConstructor_ = @Autowired)
@AutowiredConstructor
class MyComponent {
  Dependency dependency;
  // business methods
}

@SuppressWarnings("java:S2094")
class Dependency {
}

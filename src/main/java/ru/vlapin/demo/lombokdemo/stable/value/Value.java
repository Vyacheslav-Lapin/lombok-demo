package ru.vlapin.demo.lombokdemo.stable.value;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.AliasFor;

/**
 * Generates a lot of code which fits with a class that is a representation of an immutable entity.
 * <p>
 * Equivalent to {@code @Getter @FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE) @AllArgsConstructor @ToString @EqualsAndHashCode}.
 * <p>
 * Complete documentation is found at <a href="https://projectlombok.org/features/Value">the project lombok features page for &#64;Value</a>.
 *
 * @see lombok.Getter
 * @see lombok.experimental.FieldDefaults
 * @see lombok.AllArgsConstructor
 * @see lombok.ToString
 * @see lombok.EqualsAndHashCode
 * @see lombok.Data
 */
@SuppressWarnings({
//    "Lombok",
    "unused",
})

@Target(TYPE)
@Retention(SOURCE)

//@Getter
//@ToString
//@EqualsAndHashCode
//@AllArgsConstructor
//@FieldDefaults(level = PRIVATE,
//               makeFinal = true)
public @interface Value {

  /**
   * If you specify a static constructor name, then the generated constructor will be private, and
   * instead a static factory method is created that other classes can use to create instances.
   * We suggest the name: "of", like so:
   *
   * <pre>
   *     public @Value(staticConstructor = "of") class Point { final int x, y; }
   * </pre>
   *
   * Default: No static constructor, instead the normal constructor is public.
   *
   * @return Name of static 'constructor' method to generate (blank = generate a normal constructor).
   */
  @AliasFor(annotation = AllArgsConstructor.class,
            attribute = "staticName")
  String staticConstructor() default "";
}

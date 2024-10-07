package ru.vlapin.demo.lombokdemo.stable.data;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.core.annotation.AliasFor;


/**
 * Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check
 * all non-transient fields. Will also generate setters for all non-final fields, as well as a constructor
 * (except that no constructor will be generated if any explicitly written constructors already exist).
 * <p>
 * Equivalent to {@code @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode}.
 * <p>
 * Complete documentation is found at <a href="https://projectlombok.org/features/Data">the project lombok features page for &#64;Data</a>.
 *
 * @see Getter
 * @see Setter
 * @see RequiredArgsConstructor
 * @see ToString
 * @see EqualsAndHashCode
 * @see lombok.Value
 */
@SuppressWarnings({
//    "Lombok",
    "unused",
})

@Target(TYPE)
@Retention(SOURCE)

//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
//@RequiredArgsConstructor
public @interface Data {

  /**
   * If you specify a static constructor name, then the generated constructor will be private, and
   * instead a static factory method is created that other classes can use to create instances.
   * We suggest the name: "of", like so:
   *
   * <pre>
   *     public @Data(staticConstructor = "of") class Point { final int x, y; }
   * </pre>
   *
   * Default: No static constructor, instead the normal constructor is public.
   *
   * @return Name of static 'constructor' method to generate (blank = generate a normal constructor).
   */
  @AliasFor(annotation = RequiredArgsConstructor.class,
            attribute = "staticName")
  String staticConstructor() default "";
}

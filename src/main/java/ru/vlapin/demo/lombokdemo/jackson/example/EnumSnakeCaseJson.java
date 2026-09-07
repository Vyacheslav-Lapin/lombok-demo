package ru.vlapin.demo.lombokdemo.jackson.example;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;

@Retention(RUNTIME)
@Target({TYPE, FIELD})
@JacksonAnnotationsInside
@JsonSerialize(using = EnumSnakeCaseJsonSerializer.class)
@JsonDeserialize(using = EnumSnakeCaseJsonDeserializer.class)
public @interface EnumSnakeCaseJson {
}

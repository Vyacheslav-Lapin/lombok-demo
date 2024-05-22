package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.Objects;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

@UtilityClass
@ExtensionMethod(value = {
    TypeFactory.class,
    Objects.class,
}, suppressBaseMethods = false)
public class SpelUtils {

  private static @NotNull Expression getParseExpression(@Language("SpEL") String spelExpression) {
    return new SpelExpressionParser()
        .parseExpression(spelExpression);
  }

  public <T> T execute(@Language("SpEL") String self,
                       Class<? extends T> resultType) {
    return getParseExpression(self)
        .getValue(resultType)
        .requireNonNull();
  }

  @SuppressWarnings("unchecked")
  public <T> T execute(@Language("SpEL") String self,
                       ParameterizedTypeReference<? extends T> resultType) {
    return (T) execute(self, resultType.getType().rawClass());
  }

  public <T> @Nullable T executeWith(Object object,
                                     @Language("SpEL") String spelExpression,
                                     Class<? extends T> resultType) {
    return getParseExpression(spelExpression)
        .getValue(object, resultType);
  }

  @SuppressWarnings("unchecked")
  public <T> @Nullable T executeWith(Object object,
                                     @Language("SpEL") String spelExpression,
                                     ParameterizedTypeReference<? extends T> resultType) {
    return (T) executeWith(object, spelExpression, resultType.getType().rawClass());
  }
}

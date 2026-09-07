package ru.vlapin.demo.lombokdemo.common;

import java.util.Objects;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;

@ExtensionMethod(suppressBaseMethods = false, value = {
    Objects.class,
})

@UtilityClass
public class DiUtils {

  /**
   * Retrieves a bean instance from the given {@link ApplicationContext} by its name and type.
   *
   * @param <T> the type of the bean to be returned
   * @param context the {@link ApplicationContext} from which the bean will be retrieved
   * @param beanName the name of the bean to be fetched from the context
   * @param type the {@link ParameterizedTypeReference} representing the type of the bean
   * @return the bean instance cast to the specified type
   * @throws IllegalArgumentException if the resolved type is null, or the bean cannot be cast to the specified type
   */
  @SuppressWarnings("unchecked")
  public <T> T getBean(ApplicationContext context, String beanName, ParameterizedTypeReference<T> type) {
    val fetchedBean = context.getBean(beanName);
    val typeReference = (Class<T>) ResolvableType.forType(type).resolve();
    return typeReference.requireNonNull().cast(fetchedBean);
  }
}

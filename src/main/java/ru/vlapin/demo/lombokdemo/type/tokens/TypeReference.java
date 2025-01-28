package ru.vlapin.demo.lombokdemo.type.tokens;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import lombok.Getter;
import lombok.SneakyThrows;

/**
 * Ссылается на generic-тип.
 *
 * @see <a href="https://gafter.blogspot.com/2006/12/super-type-tokens.html">'Super Type Tokens' by Neal Gafter, December 04, 2006</a>
 */
public abstract class TypeReference<T> {

  @Getter private Type type;

  @Getter(lazy = true)
  @SuppressWarnings({
      "unchecked",
      "SwitchStatementWithTooFewBranches",
  })
  private Class<T> rawType = (Class<T>) switch (type) {
    case ParameterizedType parameterizedType -> parameterizedType.getRawType();
    default -> type;
  };

  @SuppressWarnings({
      "java:S112",
      "SwitchStatementWithTooFewBranches",
  })
  protected TypeReference() {
    type = switch (getClass().getGenericSuperclass()) {
      case ParameterizedType parameterizedType -> parameterizedType.getActualTypeArguments()[0];
      default -> throw new RuntimeException("Missing type parameter.");
    };
  }

  /**
   * Инстанцирует новый экземпляр {@code T}, используя конструктор по умолчанию, не имеющий аргументов.
   */
  @SneakyThrows
  public T newInstance() {
    //todo 20.12.2024: А что если интерфейс или абстрактный класс? А что если конструктора без параметров нету или он не публичный?
    return getRawType().getConstructor()
                       .newInstance();
  }
}

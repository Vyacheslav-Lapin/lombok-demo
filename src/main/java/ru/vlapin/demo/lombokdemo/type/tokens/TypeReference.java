package ru.vlapin.demo.lombokdemo.type.tokens;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.SneakyThrows;

/**
 * Ссылается на generic-тип.
 *
 * @author crazybob@google.com (Bob Lee)
 * @see <a href="https://gafter.blogspot.com/2006/12/super-type-tokens.html">'Super Type Tokens' by Neal Gafter, December 04, 2006</a>
 */
public abstract class TypeReference<T> {

  @Getter private Type type;

  @Getter(lazy = true)
  @SuppressWarnings({"unchecked", "SwitchStatementWithTooFewBranches"})
  private Class<T> rawType = (Class<T>) switch (type) {
    case ParameterizedType parameterizedType -> parameterizedType.getRawType();
    default -> type;
  };

  @SuppressWarnings({"SwitchStatementWithTooFewBranches", "java:S112"})
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
    return getRawType().getConstructor()
                       .newInstance();
  }

  public static void main(String[] args) throws Exception {
    List<String> l1 = new TypeReference<ArrayList<String>>() {}.newInstance();
    List l2 = new TypeReference<ArrayList>() {}.newInstance();
  }
}

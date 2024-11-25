package ru.vlapin.demo.lombokdemo.type.tokens;

import java.util.HashMap;
import java.util.Map;
import lombok.val;

/**
 * A container class that allows storing and retrieving objects by their class type.
 * This class uses a {@link Map} to associate a given class type with an object,
 * enabling type-safe retrieval of objects.
 *
 * @author Neal Gafter
 *
 * @see <a href="https://gafter.blogspot.com/2006/12/super-type-tokens.html">'Super Type Tokens' by Neal Gafter, December 04, 2006</a>
 */
public class Favorites {

  private Map<Class<?>, Object> favorites = new HashMap<>();

  public <T> void setFavorite(Class<T> clazz, T thing) {
    favorites.put(clazz, thing);
  }

  public <T> T getFavorite(Class<T> clazz) {
    return clazz.cast(favorites.get(clazz));
  }

  public static void main(String[] __) {
    val f = new Favorites();
    f.setFavorite(String.class, "Java");
    f.setFavorite(Integer.class, 0xcafebabe);
    String s = f.getFavorite(String.class);
    int i = f.getFavorite(Integer.class);
  }
}

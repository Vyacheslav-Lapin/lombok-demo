package ru.vlapin.demo.lombokdemo.common;

import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.Contract;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Character.*;
import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;

@SuppressWarnings("unused")

@UtilityClass
public class CharSequenceUtil {

  public String capitalize(CharSequence s) {
    return "%s%s".formatted(toUpperCase(s.charAt(0)), s.subSequence(1, s.length()));
  }

  public CharSequence camelCaseToSpacedString(CharSequence camelCase) {
    return camelCase.chars()
        .mapMulti((c, consumer) -> {
          if (isUpperCase(c))
            consumer.accept(' ');
          consumer.accept(toLowerCase(c));
        })
        .mapToObj(c -> (char) c)
        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
  }

  public Map<Character, Integer> countDuplicateCharacters(CharSequence s) {
    val result = new HashMap<Character, Integer>();
    for (char c : s.toString().toCharArray())
      result.compute(c, (character, integer) -> integer == null ? 1 : integer + 1);
    return result;
  }

  public Map<Character, Long> countDuplicateCharactersJava8(CharSequence s) {
    return s.chars()
        .mapToObj(character -> (char) character)
        .collect(groupingBy(identity(), counting()));
  }

  @Contract(pure = true)
  public Map<Character, Integer> getCharCount(CharSequence chars) {
    return chars.chars().collect(
        HashMap::new,
        (hashMap, value) -> hashMap.merge((char) value, 1, Integer::sum),
        HashMap::putAll);
  }
}

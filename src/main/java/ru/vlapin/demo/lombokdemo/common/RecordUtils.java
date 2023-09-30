package ru.vlapin.demo.lombokdemo.common;

import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.lang.reflect.Constructor;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;

@SuppressWarnings("unused")

@UtilityClass
@ExtensionMethod({
    Arrays.class,
})
public class RecordUtils {

  /**
   * Получение канонического конструктора по типу record'а.
   * @param recordClass тип record'а
   * @return канонический конструктор record'а
   * @param <T> тип record'а
   */
  @SneakyThrows
  public <T extends Record> Constructor<T> canonicalConstructor(Class<T> recordClass) {
    val parameterTypes = recordClass.getRecordComponents().stream()
        .map(RecordComponent::getType)
        .toArray(Class<?>[]::new);
    return recordClass.getDeclaredConstructor(parameterTypes);
  }
}

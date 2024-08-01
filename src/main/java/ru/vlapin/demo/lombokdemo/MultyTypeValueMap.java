package ru.vlapin.demo.lombokdemo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.experimental.Delegate;

interface MultiTypeValueMapKey<T> {

  @SuppressWarnings("unchecked")
  default Optional<T> get(MultyTypeValueMap<MultiTypeValueMapKey<T>> map) {
    return Optional.ofNullable((T) map.get(this));
  }
}

public class MultyTypeValueMap<K extends MultiTypeValueMapKey<?>> implements Map<K, Object> {

  @Delegate
  Map<K, Object> map = new HashMap<>();

  public <V> MultyTypeValueMap<K> putTyped(K key, V value) {
    return this;
  }
}

package ru.vlapin.demo.lombokdemo.stable.builder.singular.map.guava;

import static lombok.AccessLevel.*;

import com.google.common.collect.ImmutableSortedMap;
import java.util.Map;
import java.util.NavigableMap;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@SuppressWarnings({"java:S4968", "java:S1117"})
//@Builder
public record PersonNavigable(
//@Singular
    NavigableMap<String, Integer> contacts) {

  public static PersonNavigableBuilder builder() {
    return new PersonNavigableBuilder();
  }

  @Setter
  @ToString
  @Accessors(fluent = true)
  @FieldDefaults(level = PRIVATE)
  @NoArgsConstructor(access = PACKAGE)
  public static class PersonNavigableBuilder {

    ImmutableSortedMap.Builder<String, Integer> contacts;

    public PersonNavigable.PersonNavigableBuilder contact(String key, Integer value) {
      if (contacts == null) {
        contacts = ImmutableSortedMap.naturalOrder();
      }
      contacts.put(key, value);
      return this;
    }

    public PersonNavigable.PersonNavigableBuilder contacts(Map<? extends String, ? extends Integer> contacts) {
      if (contacts == null) {
        throw new NullPointerException("contacts cannot be null");
      }
      if (this.contacts == null) {
        this.contacts = ImmutableSortedMap.naturalOrder();
      }
      this.contacts.putAll(contacts);
      return this;
    }

    public PersonNavigable.PersonNavigableBuilder clearContacts() {
      contacts = null;
      return this;
    }

    public PersonNavigable build() {
      NavigableMap<String, Integer> contacts = this.contacts == null ? ImmutableSortedMap.of() : this.contacts.build();
      return new PersonNavigable(contacts);
    }
  }
}

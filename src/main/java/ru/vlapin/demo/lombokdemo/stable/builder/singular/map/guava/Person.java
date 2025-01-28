package ru.vlapin.demo.lombokdemo.stable.builder.singular.map.guava;

import static lombok.AccessLevel.*;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@SuppressWarnings({"java:S4968", "java:S1117"})
//@Builder
public record Person(
//@Singular
    Map<String, Integer> contacts) {

  public static PersonBuilder builder() {
    return new PersonBuilder();
  }

  @Setter
  @ToString
  @Accessors(fluent = true)
  @NoArgsConstructor(access = PACKAGE)
  @FieldDefaults(level = PRIVATE)
  public static class PersonBuilder {

    ImmutableMap.Builder<String, Integer> contacts;

    public PersonBuilder contact(String key, Integer value) {
      if (this.contacts == null) {
        this.contacts = ImmutableMap.builder();
      }
      this.contacts.put(key, value);
      return this;
    }

    public PersonBuilder contacts(Map<? extends String, ? extends Integer> contacts) {
      if (contacts == null) {
        throw new NullPointerException("contacts cannot be null");
      }
      if (this.contacts == null) {
        this.contacts = ImmutableMap.builder();
      }
      this.contacts.putAll(contacts);
      return this;
    }

    public PersonBuilder clearContacts() {
      this.contacts = null;
      return this;
    }

    public Person build() {
      Map<String, Integer> contacts = this.contacts == null ? ImmutableMap.of() : this.contacts.build();
      return new Person(contacts);
    }
  }
}

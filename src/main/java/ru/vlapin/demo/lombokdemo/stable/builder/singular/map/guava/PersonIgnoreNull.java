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
public record PersonIgnoreNull(
//@Singular(ignoreNullCollections = true)
    Map<String, String> contacts) {

  public static PersonIgnoreNullBuilder builder() {
    return new PersonIgnoreNullBuilder();
  }

  @Setter
  @ToString
  @Accessors(fluent = true)
  @FieldDefaults(level = PRIVATE)
  @NoArgsConstructor(access = PACKAGE)
  public static class PersonIgnoreNullBuilder {

    ImmutableMap.Builder<String, String> contacts;

    public PersonIgnoreNull.PersonIgnoreNullBuilder contact(String key, String value) {
      if (contacts == null) {
        contacts = ImmutableMap.builder();
      }
      contacts.put(key, value);
      return this;
    }

    public PersonIgnoreNull.PersonIgnoreNullBuilder contacts(Map<? extends String, ? extends String> contacts) {
      if (contacts != null) {
        if (this.contacts == null) {
          this.contacts = ImmutableMap.builder();
        }
        this.contacts.putAll(contacts);
      }
      return this;
    }

    public PersonIgnoreNull.PersonIgnoreNullBuilder clearContacts() {
      contacts = null;
      return this;
    }

    public PersonIgnoreNull build() {
      Map<String, String> contacts = this.contacts == null ? ImmutableMap.of() : this.contacts.build();
      return new PersonIgnoreNull(contacts);
    }
  }
}

package ru.vlapin.demo.lombokdemo.stable.builder.singular.map.guava;

import static lombok.AccessLevel.*;

import com.google.common.collect.ImmutableSortedMap;
import java.util.Map;
import java.util.SortedMap;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@SuppressWarnings({"java:S4968", "java:S1117"})
//@Builder
public record PersonSorted(
//  @Singular
  SortedMap<String, Integer> contacts) {

  @Setter
  @ToString
  @Accessors(fluent = true)
  @FieldDefaults(level = PRIVATE)
  @NoArgsConstructor(access = PACKAGE)
  public static class PersonSortedBuilder {
    ImmutableSortedMap.Builder<String, Integer> contacts;

    public PersonSorted.PersonSortedBuilder contact(String key, Integer value) {
      if (this.contacts == null) this.contacts = ImmutableSortedMap.naturalOrder();
      this.contacts.put(key, value);
      return this;
    }

    public PersonSorted.PersonSortedBuilder contacts(Map<? extends String, ? extends Integer> contacts) {
      if (contacts == null) {
        throw new NullPointerException("contacts cannot be null");
      }
      if (this.contacts == null) this.contacts = ImmutableSortedMap.naturalOrder();
      this.contacts.putAll(contacts);
      return this;
    }

    public PersonSorted.PersonSortedBuilder clearContacts() {
      this.contacts = null;
      return this;
    }

    public PersonSorted build() {
      SortedMap<String, Integer> contacts = this.contacts == null ? ImmutableSortedMap.of() : this.contacts.build();
      return new PersonSorted(contacts);
    }
  }

  public static PersonSorted.PersonSortedBuilder builder() {
    return new PersonSorted.PersonSortedBuilder();
  }
}

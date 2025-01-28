package ru.vlapin.demo.lombokdemo.stable.builder.singular.set;

import static lombok.AccessLevel.*;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@SuppressWarnings({"java:S4968", "java:S1117"})
//@Builder
public record PersonIgnoreNull(
//    @Singular(ignoreNullCollections = true)
    Set<String> contacts) {

  public static PersonIgnoreNullBuilder builder() {
    return new PersonIgnoreNullBuilder();
  }

  @Setter
  @ToString
  @Accessors(fluent = true)
  @FieldDefaults(level = PRIVATE)
  @NoArgsConstructor(access = PACKAGE)
  public static class PersonIgnoreNullBuilder {

    ImmutableSet.Builder<String> contacts;

    public PersonIgnoreNullBuilder contact(String contact) {
      if (contacts == null) {
        contacts = ImmutableSet.builder();
      }
      contacts.add(contact);
      return this;
    }

    public PersonIgnoreNullBuilder contacts(Iterable<? extends String> contacts) {
      if (contacts != null) {
        if (this.contacts == null) {
          this.contacts = ImmutableSet.builder();
        }
        this.contacts.addAll(contacts);
      }
      return this;
    }

    public PersonIgnoreNullBuilder clearContacts() {
      contacts = null;
      return this;
    }

    public PersonIgnoreNull build() {
      Set<String> contacts = this.contacts == null ? ImmutableSet.of() : this.contacts.build();
      return new PersonIgnoreNull(contacts);
    }
  }
}

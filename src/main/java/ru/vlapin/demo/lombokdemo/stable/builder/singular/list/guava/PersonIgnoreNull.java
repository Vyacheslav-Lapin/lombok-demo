package ru.vlapin.demo.lombokdemo.stable.builder.singular.list.guava;

import static lombok.AccessLevel.*;

import com.google.common.collect.ImmutableList;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

//@Builder
public record PersonIgnoreNull(
//@Singular(ignoreNullCollections = true)
    List<String> contacts) {

  public static PersonIgnoreNullBuilder builder() {
    return new PersonIgnoreNullBuilder();
  }

  @Setter
  @ToString
  @Accessors(fluent = true)
  @FieldDefaults(level = PRIVATE)
  @NoArgsConstructor(access = PACKAGE)
  public static class PersonIgnoreNullBuilder {

    ImmutableList.Builder<String> contacts;

    public PersonIgnoreNull.PersonIgnoreNullBuilder contact(String contact) {
      if (contacts == null) {
        contacts = ImmutableList.builder();
      }
      contacts.add(contact);
      return this;
    }

    public PersonIgnoreNull.PersonIgnoreNullBuilder contacts(Iterable<? extends String> contacts) {
      if (contacts != null) {
        if (this.contacts == null) {
          this.contacts = ImmutableList.builder();
        }
        this.contacts.addAll(contacts);
      }
      return this;
    }

    public PersonIgnoreNull.PersonIgnoreNullBuilder clearContacts() {
      contacts = null;
      return this;
    }

    public PersonIgnoreNull build() {
      List<String> contacts = this.contacts == null ? ImmutableList.of() : this.contacts.build();
      return new PersonIgnoreNull(contacts);
    }
  }
}

package ru.vlapin.demo.lombokdemo.stable.builder.singular.list.guava;

import static lombok.AccessLevel.*;

import com.google.common.collect.ImmutableList;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

//@Builder//
public record Person(
//@Singular
    List<String> contacts) {

  public static PersonBuilder builder() {
    return new PersonBuilder();
  }

  @Setter
  @ToString
  @NoArgsConstructor(access = PACKAGE)
  @Accessors(fluent = true)
  @FieldDefaults(level = PRIVATE)
  public static class PersonBuilder {

    private ImmutableList.Builder<String> contacts;

    public PersonBuilder contact(String contact) {
      if (contacts == null) {
        contacts = ImmutableList.builder();
      }
      contacts.add(contact);
      return this;
    }

    public PersonBuilder contacts(Iterable<? extends String> contacts) {
      if (contacts == null) {
        throw new NullPointerException("contacts cannot be null");
      }
      if (this.contacts == null) {
        this.contacts = ImmutableList.builder();
      }
      this.contacts.addAll(contacts);
      return this;
    }

    public PersonBuilder clearContacts() {
      contacts = null;
      return this;
    }

    public Person build() {
      List<String> contacts = this.contacts == null ? ImmutableList.of() : this.contacts.build();
      return new Person(contacts);
    }
  }
}

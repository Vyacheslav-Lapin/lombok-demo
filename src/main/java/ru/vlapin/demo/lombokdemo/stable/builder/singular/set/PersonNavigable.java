package ru.vlapin.demo.lombokdemo.stable.builder.singular.set;

import static lombok.AccessLevel.*;

import com.google.common.collect.ImmutableSortedSet;
import java.util.NavigableSet;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@SuppressWarnings({"java:S4968", "java:S1117"})
//@Builder
public record PersonNavigable(
//  @Singular
    NavigableSet<String> contacts) {

  public static PersonNavigableBuilder builder() {
    return new PersonNavigableBuilder();
  }

  @Setter
  @ToString
  @Accessors(fluent = true)
  @FieldDefaults(level = PRIVATE)
  @NoArgsConstructor(access = PACKAGE)
  public static class PersonNavigableBuilder {

    ImmutableSortedSet.Builder<String> contacts;

    public PersonNavigableBuilder contact(String contact) {
      if (contacts == null) {
        contacts = ImmutableSortedSet.naturalOrder();
      }
      contacts.add(contact);
      return this;
    }

    public PersonNavigableBuilder contacts(Iterable<? extends String> contacts) {
      if (contacts == null) {
        throw new NullPointerException("contacts cannot be null");
      }
      if (this.contacts == null) {
        this.contacts = ImmutableSortedSet.naturalOrder();
      }
      this.contacts.addAll(contacts);
      return this;
    }

    public PersonNavigableBuilder clearContacts() {
      contacts = null;
      return this;
    }

    public PersonNavigable build() {
      NavigableSet<String> contacts = this.contacts == null ? ImmutableSortedSet.of() : this.contacts.build();
      return new PersonNavigable(contacts);
    }
  }
}

package ru.vlapin.demo.lombokdemo.stable.builder.singular.list;

import static lombok.AccessLevel.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@SuppressWarnings({"java:S2259", "java:S4968", "java:S1117", "Java9CollectionFactory", "DuplicatedCode"})
//@SuppressWarnings("java:S125")
//@Builder
public record Person(
//  @Singular
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

    ArrayList<String> contacts;

    public PersonBuilder contact(String contact) {
      if (contacts == null) {
        contacts = new ArrayList<>();
      }
      contacts.add(contact);
      return this;
    }

    public PersonBuilder contacts(@NonNull Collection<? extends String> contacts) {
      if (this.contacts == null) {
        this.contacts = new ArrayList<>();
      }
      this.contacts.addAll(contacts);
      return this;
    }

    public PersonBuilder clearContacts() {
      if (contacts != null) {
        contacts.clear();
      }
      return this;
    }

    public Person build() {
      List<String> contacts = switch (this.contacts == null ? 0 : this.contacts.size()) {
        case 0 -> Collections.emptyList();
        case 1 -> Collections.singletonList(this.contacts.getFirst());
        default -> Collections.unmodifiableList(new ArrayList<>(this.contacts));
      };
      return new Person(contacts);
    }
  }
}

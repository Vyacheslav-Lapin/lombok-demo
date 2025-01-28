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
public record PersonSingle(
//@Singular("contactEntry")
    List<String> contact) {

  public static PersonSingleBuilder builder() {
    return new PersonSingleBuilder();
  }

  @Setter
  @ToString
  @Accessors(fluent = true)
  @FieldDefaults(level = PRIVATE)
  @NoArgsConstructor(access = PACKAGE)
  public static class PersonSingleBuilder {

    ImmutableList.Builder<String> contact;

    public PersonSingleBuilder contactEntry(String contactEntry) {
      if (contact == null) {
        contact = ImmutableList.builder();
      }
      contact.add(contactEntry);
      return this;
    }

    public PersonSingleBuilder contact(Iterable<? extends String> contact) {
      if (contact == null) {
        throw new NullPointerException("contact cannot be null");
      }
      if (this.contact == null) {
        this.contact = ImmutableList.builder();
      }
      this.contact.addAll(contact);
      return this;
    }

    public PersonSingleBuilder clearContact() {
      contact = null;
      return this;
    }

    public PersonSingle build() {
      List<String> contact = this.contact == null ? ImmutableList.of() : this.contact.build();
      return new PersonSingle(contact);
    }
  }
}

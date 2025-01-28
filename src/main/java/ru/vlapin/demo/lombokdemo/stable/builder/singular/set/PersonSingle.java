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
public record PersonSingle(
//  @Singular("contactEntry")
    Set<String> contact) {

  public static PersonSingleBuilder builder() {
    return new PersonSingleBuilder();
  }

  @Setter
  @ToString
  @Accessors(fluent = true)
  @FieldDefaults(level = PRIVATE)
  @NoArgsConstructor(access = PACKAGE)
  public static class PersonSingleBuilder {

    private ImmutableSet.Builder<String> contact;

    public PersonSingleBuilder contactEntry(String contactEntry) {
      if (contact == null) {
        contact = ImmutableSet.builder();
      }
      contact.add(contactEntry);
      return this;
    }

    public PersonSingleBuilder contact(Iterable<? extends String> contact) {
      if (contact == null) {
        throw new NullPointerException("contact cannot be null");
      }
      if (this.contact == null) {
        this.contact = ImmutableSet.builder();
      }
      this.contact.addAll(contact);
      return this;
    }

    public PersonSingleBuilder clearContact() {
      contact = null;
      return this;
    }

    public PersonSingle build() {
      Set<String> contact = this.contact == null ? ImmutableSet.of() : this.contact.build();
      return new PersonSingle(contact);
    }
  }
}

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
public record PersonSingle(
//@Singular("contactEntry")
  Map<String, String> contact) {

  @Setter
  @ToString
  @Accessors(fluent = true)
  @NoArgsConstructor(access = PACKAGE)
  @FieldDefaults(level = PRIVATE)
  public static class PersonSingleBuilder {
    ImmutableMap.Builder<String, String> contact;

    public PersonSingle.PersonSingleBuilder contactEntry(String key, String value) {
      if (contact == null) contact = ImmutableMap.builder();
      contact.put(key, value);
      return this;
    }

    public PersonSingle.PersonSingleBuilder contact(Map<? extends String, ? extends String> contact) {
      if (contact == null) {
        throw new NullPointerException("contact cannot be null");
      }
      if (this.contact == null) this.contact = ImmutableMap.builder();
      this.contact.putAll(contact);
      return this;
    }

    public PersonSingle.PersonSingleBuilder clearContact() {
      contact = null;
      return this;
    }

    public PersonSingle build() {
      Map<String, String> contact = this.contact == null ? ImmutableMap.of() : this.contact.build();
      return new PersonSingle(contact);
    }
  }

  public static PersonSingleBuilder builder() {
    return new PersonSingleBuilder();
  }
}

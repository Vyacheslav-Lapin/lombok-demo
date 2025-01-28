package ru.vlapin.demo.lombokdemo.stable.builder.singular.map;

import java.util.Map;
import lombok.Builder;
import lombok.Singular;

@Builder
public record Person(
    @Singular Map<String, Integer> contacts) {

//public static PersonBuilder builder() {
//  return new PersonBuilder();
//}

//@Setter @ToString @FieldDefaults(level = PRIVATE)
//@Accessors(fluent = true) @NoArgsConstructor(access = PROTECTED)
//public static class PersonBuilder {

//  ArrayList<String> contacts$key;
//  ArrayList<Integer> contacts$value;

//  public PersonBuilder contact(String contactKey, Integer contactValue) {
//    if (contacts$key == null) {
//      contacts$key = new ArrayList<>();
//      contacts$value = new ArrayList<>();
//    }
//    contacts$key.add(contactKey);
//    contacts$value.add(contactValue);
//    return this;
//  }

//  public PersonBuilder contacts(@NonNull Map<? extends String, ? extends Integer> contacts) {
//    if (contacts$key == null) {
//      contacts$key = new ArrayList<>();
//      contacts$value = new ArrayList<>();
//    }
//    for (Entry<? extends String, ? extends Integer> $lombokEntry : contacts.entrySet()) {
//      contacts$key.add($lombokEntry.getKey());
//      contacts$value.add($lombokEntry.getValue());
//    }
//    return this;
//  }

//  public PersonBuilder clearContacts() {
//    if (contacts$key != null) {
//      contacts$key.clear();
//      contacts$value.clear();
//    }
//    return this;
//  }

//  @SuppressWarnings({"java:S2259", "java:S6485"})
//  public Person build() {
//    Map<String, Integer> contacts = switch (contacts$key == null ? 0 : contacts$key.size()) {
//      case 0 -> Collections.emptyMap();
//      case 1 -> Collections.singletonMap(contacts$key.getFirst(), contacts$value.getFirst());
//      default -> {
//        contacts = new LinkedHashMap<>(
//            contacts$key.size() < 1_073_741_824
//                ? 1 + contacts$key.size() + (contacts$key.size() - 3) / 3
//                : Integer.MAX_VALUE);
//        for (int $i = 0; $i < contacts$key.size(); $i++) {
//          contacts.put(contacts$key.get($i), contacts$value.get($i));
//        }
//        yield Collections.unmodifiableMap(contacts);
//      }
//    };
//    return new Person(contacts);
//  }
//}
}

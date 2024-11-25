package ru.vlapin.demo.lombokdemo.stable.builder.dsl;

import java.util.function.UnaryOperator;
import lombok.Builder;
import lombok.experimental.Tolerate;

@Builder
public record Address (String city,
                       String street,
                       int houseNumber,
                       int apartment) {

  @Tolerate
  @SuppressWarnings("LombokBuilder")
  public static Address builder(UnaryOperator<Address.AddressBuilder> config) {
    return config.apply(new Address.AddressBuilder()).build();
  }
}

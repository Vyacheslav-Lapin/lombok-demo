package ru.vlapin.demo.lombokdemo.model;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

/**
 * ClientTest.
 */
@SuppressWarnings("java:S2699")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringBootTest(properties = {
    "spring.autoconfigure.exclude="
        + "org.springdoc.core.configuration.SpringDocDataRestConfiguration"
        + ",org.springdoc.core.configuration.SpringDocHateoasConfiguration"})
@DisplayNameGeneration(ReplaceCamelCase.class)
class ClientTest {

//  ClientRepository clientRepository;
  @Test
  //@DisplayName("Client with payments creates correctly")
  void clientWithPaymentsCreatesCorrectlyTest() {

    // given
//    val clientWithoutPayments = clientRepository.save(
//        Client.builder()
//            .city("Moscow")
//            .name("Bruce").build());
//
//    Stream.range(0, 10)
//        .map(__ -> Payment.builder()
//            .amount(BigDecimal.valueOf(1_000))
//            .client(clientWithoutPayments).build())
//        .forEach(clientWithoutPayments.getPayments()::add);
//
//    val clientWithPayments = clientRepository.save(clientWithoutPayments);
//
//     when
//    assertThat(clientWithPayments.getPayments()).isNotNull()
//         then
//        .hasSize(10);
  }
}

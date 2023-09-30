package ru.vlapin.demo.lombokdemo.model;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * ClientTest.
 */
@SpringBootTest
@Testcontainers
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SuppressWarnings("java:S2699")
class ClientTest {

  @Container
  @ServiceConnection
  @SuppressWarnings("unused")
  static PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>("postgres:latest");

//  ClientRepository clientRepository;
  @Test
  @DisplayName("Client with payments creates correctly")
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

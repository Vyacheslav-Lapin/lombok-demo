package ru.vlapin.demo.lombokdemo.first.example;

import java.time.LocalDate;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.time.Month.*;
import static org.assertj.core.api.Assertions.*;

class EmployeeTests {

  @Test
  @SneakyThrows
  @DisplayName("First example works correctly")
  void firstExampleWorksCorrectlyTest() {
    //Сколько кода нужно написать, что бы работало:
    Employee vasilisaPupkina =
        Employee.builder()
            .firstName("Василиса")
            .companyName("CoolCompany")
            .lastName("Пупкина")
            .dateOfBirth(
                LocalDate.of(1986, JANUARY, 23))
            .address("Nск, Qwerty'вская улица, 123")
            .position("Senior Developer").build();

    Employee vasilisaSidorova =
        vasilisaPupkina.toBuilder()
            .companyName("XYZ Inc.")
            .lastName("Сидорова")
            .position("Lead Developer").build();

    //    System.out.println(vasilisaSidorova);
    // Employee(super=Person(firstName=Василиса, \
    // lastName=Сидорова, dateOfBirth=1986-01-23, \
    // address=Nск, Qwerty'вская улица, 123, \
    // telephone=null), \
    // companyName=XYZ Inc., position=Lead Developer)

    assertThat(vasilisaSidorova).isNotNull()
        .isExactlyInstanceOf(Employee.class)
        .matches(it -> it.getFirstName().equals("Василиса"))
        .matches(it -> it.getLastName().equals("Сидорова"))
        .matches(it -> it.getDateOfBirth().isEqual(LocalDate.of(1986, JANUARY, 23)))
        .matches(it -> it.getAddress().equals("Nск, Qwerty'вская улица, 123"))
        .matches(it -> it.getTelephone() == null)
        .matches(it -> it.getCompanyName().equals("XYZ Inc."))
        .matches(it -> it.getPosition().equals("Lead Developer"));
  }
}

package ru.vlapin.demo.lombokdemo.tasks;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.tasks.MedicalRecords.*;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MedicalRecordsTest {

  /**
   * Имя врача и идентификатор диагноза ищутся в записях, и возвращаются минимальная и максимальная температуры тела.
   */
  @Test
  @DisplayName("sample case 0")
  void sampleCase0Test() {
    // given
    val bodyTemperature = bodyTemperature("Dr Arnold Bullock", 2);

    // when
    assertThat(bodyTemperature).isNotNull()
                               // then
                               .hasSize(2)
                               .contains(99, 103);
  }

  /**
   * Имя врача и идентификатор диагноза ищутся в записях, и возвращаются минимальная и максимальная температуры тела.
   */
  @Test
  @DisplayName("sample case 1")
  void sampleCase1Test() {
    // given
    val bodyTemperature = bodyTemperature("Dr Allysa Ellis", 2);

    // when
    assertThat(bodyTemperature).isNotNull()
                             // then
                             .hasSize(2)
                             .contains(99, 104);
  }
}

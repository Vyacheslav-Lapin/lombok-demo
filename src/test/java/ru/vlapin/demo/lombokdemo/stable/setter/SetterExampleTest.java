package ru.vlapin.demo.lombokdemo.stable.setter;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Вы можете пометить любое поле с помощью аннотаций @{@link Getter} и / или @{@link Setter},
 * чтобы lombok автоматически сгенерировал getter'ы и setter'ы.
 * <p>если поле называется 'foo', то getter для него по умолчанию будет просто возвращать
 * поле и получит имя 'getFoo' (или 'isFoo' если тип поля - <code>boolean</code>).</p>
 * <p>Setter'ы по умолчанию называются 'setFoo', если поле называется 'foo', возвращают
 * <code>void</code> и принимают по одному аргументу того же типа, что и аннотированное поле,
 * просто присваивая полю значение.</p>
 */
class SetterExampleTest {

  @Test
  @SneakyThrows
  @DisplayName("Setter Lombok annotation works correctly")
  void setterLombokAnnotationWorksCorrectlyTest() {
    // given
    // Как добиться:
    var se = new SetterExample();

    // when
    se.setX(5);

    assertThat(se).isNotNull()
        // then
        .extracting(SetterExample::getX)
        .isEqualTo(5);
  }
}

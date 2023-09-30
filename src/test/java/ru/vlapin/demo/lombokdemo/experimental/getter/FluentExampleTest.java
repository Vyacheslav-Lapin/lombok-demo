package ru.vlapin.demo.lombokdemo.experimental.getter;

import lombok.val;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

import static org.assertj.core.api.Assertions.*;

@DisplayNameGeneration(ReplaceCamelCase.class)
class FluentExampleTest {

  @Test
//  @DisplayName("fluent accessor param works correctly")
  void fluentAccessorParamWorksCorrectlyTest() {
    // given
    val fe = new FluentExample()
        .s("Lorem ipsum");
    fe.x(5);

    // when
    assertThat(fe).isNotNull()
        // then
        .extracting(
            FluentExample::x,
            FluentExample::s)
        .contains(5, "Lorem ipsum");
  }
}

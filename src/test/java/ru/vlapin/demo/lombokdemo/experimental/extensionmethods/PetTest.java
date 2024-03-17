package ru.vlapin.demo.lombokdemo.experimental.extensionmethods;

import static org.assertj.core.api.Assertions.*;

import java.util.function.Function;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import ru.vlapin.demo.lombokdemo.common.TestUtils.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
class PetTest {

  @Test
  void aDynamicMethodWithoutParametersCanBeRepresentedAsASupplierTest() {
    // given
    Supplier<Long> getIdAsSupplier = new Pet()::getId;
    Long id = getIdAsSupplier.get();

    // when
    assertThat(id).isNotNull()
        // then
        .isEqualTo(1L);
  }

  @Test
  void aDynamicMethodWithoutParametersCanBeRepresentedAsAFunctionWithOneParameterTest() {
    // given
    Function<Pet, Long> getIdAsFunction = Pet::getId;
    Long id = getIdAsFunction.apply(new Pet());

    // when
    assertThat(id).isNotNull()
        // then
        .isEqualTo(1L);
  }

  @Test
  void aDynamicMethodWithoutParametersCanBeTransformedToSupplierTest() {
    // given
    Function<Pet, Long> getIdAsFunction = Pet::getId;
    Supplier<Long> getIdAsSupplier = () -> getIdAsFunction.apply(new Pet());
    Long id = getIdAsSupplier.get();

    // when
    assertThat(id).isNotNull()
                  // then
                  .isEqualTo(1L);
  }
}

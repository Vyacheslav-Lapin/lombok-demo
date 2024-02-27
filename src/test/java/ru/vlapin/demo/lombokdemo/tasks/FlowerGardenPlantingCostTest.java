package ru.vlapin.demo.lombokdemo.tasks;

import static org.assertj.core.api.Assertions.*;
import static ru.vlapin.demo.lombokdemo.tasks.FlowerGardenPlantingCost.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FlowerGardenPlantingCostTest {

  @Test
  @DisplayName("Minimal flower garden planting cost calculated correctly")
  void minimalFlowerGardenPlantingCostCalculatedCorrectlyTest() {
    // given
    int[][] costs = {{7, 4, 10}, {3, 6, 5}, {8, 1, 12}};

    // when
    assertThat(minCost(costs))
        // then
        .isEqualTo(8);

  }
}

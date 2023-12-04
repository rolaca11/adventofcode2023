package io.ropi.adventofcode2023.day3

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Stage2KtTest {

    @Test
    fun stage2Example() {
        Assertions.assertThat(
            this::class.java.classLoader.getResource("stage1-example")?.let { stage2(it.readText()) }
        ).isEqualTo(467835)
    }

    @Test
    fun stage2Actual() {
        Assertions.assertThat(
            this::class.java.classLoader.getResource("actual")?.let { stage2(it.readText()) }
        ).isEqualTo(75519888)
    }
}
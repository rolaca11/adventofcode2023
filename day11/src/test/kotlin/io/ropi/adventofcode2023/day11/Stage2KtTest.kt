package io.ropi.adventofcode2023.day11

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Stage2KtTest {
    @Test
    fun stage2Example() {
        Assertions.assertThat(
            this::class.java.classLoader.getResource("stage1-example")?.let { stage2(it.readText(), 10) })
            .isEqualTo(1030)
        Assertions.assertThat(
            this::class.java.classLoader.getResource("stage1-example")?.let { stage2(it.readText(), 100) })
            .isEqualTo(8410)
    }

    @Test
    fun stage2Actual() {
        Assertions.assertThat(
            this::class.java.classLoader.getResource("actual")?.let { stage2(it.readText(), 1000000) })
            .isEqualTo(840988812853)
    }
}
package io.ropi.adventofcode2023.day9

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Stage1KtTest {
    @Test
    fun stage1Example() {
        Assertions.assertThat(
            this::class.java.classLoader.getResource("stage1-example")?.let { stage1(it.readText()) })
            .isEqualTo(114)
    }

    @Test
    fun stage1Actual() {
        Assertions.assertThat(
            this::class.java.classLoader.getResource("actual")?.let { stage1(it.readText()) })
            .isEqualTo(114)
    }
}
package io.ropi.adventofcode2023.day10

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class Stage2KtTest {
    @Test
    fun stage2Example() {
        assertThat(
            this::class.java.classLoader.getResource("stage2-example1")?.let { stage2(it.readText()) })
            .isEqualTo(4)
        assertThat(
            this::class.java.classLoader.getResource("stage2-example4")?.let { stage2(it.readText()) })
            .isEqualTo(4)
    }

    @Test
    fun stage2Example2() {
        assertThat(
            this::class.java.classLoader.getResource("stage2-example2")?.let { stage2(it.readText()) })
            .isEqualTo(8)
        assertThat(
            this::class.java.classLoader.getResource("stage2-example3")?.let { stage2(it.readText()) })
            .isEqualTo(10)
    }

    @Test
    fun stage2Actual() {
        assertThat(
            this::class.java.classLoader.getResource("actual")?.let { stage2(it.readText()) })
            .isGreaterThan(252).isLessThan(793).isEqualTo(252)
    }
}
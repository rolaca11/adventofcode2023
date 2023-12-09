package io.ropi.adventofcode2023.day8

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Stage1KtTest {
    @Test
    fun stage1Example() {
        assertThat(this::class.java.classLoader.getResource("example1")?.let { stage1(it.readText()) })
            .isEqualTo(2)
        assertThat(this::class.java.classLoader.getResource("example2")?.let { stage1(it.readText()) })
            .isEqualTo(6)
    }

    @Test
    fun stage1Actual() {
        assertThat(this::class.java.classLoader.getResource("actual")?.let { stage1(it.readText()) })
            .isEqualTo(13207)
    }
}
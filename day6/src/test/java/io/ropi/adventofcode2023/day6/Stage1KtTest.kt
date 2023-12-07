package io.ropi.adventofcode2023.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Stage1KtTest {
    @Test
    fun stage1Example() {
        assertThat(this::class.java.classLoader.getResource("stage1-example")?.let { stage1(it.readText()) })
            .isEqualTo(288)
    }

    @Test
    fun stage1Actual() {
        assertThat(this::class.java.classLoader.getResource("actual")?.let { stage1(it.readText()) })
            .isEqualTo(160816)
    }
}
package io.ropi.adventofcode2023.day2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Stage1Test {

    @Test
    fun stage1Example() {
        assertThat(this::class.java.classLoader.getResource("stage1-example")?.let {
            stage1(
                input = it.readText(),
                existingColors = mapOf(
                    Color.RED to 12,
                    Color.GREEN to 13,
                    Color.BLUE to 14
                )
            )
        }).isEqualTo(8)
    }

    @Test
    fun stage1Actual() {
        assertThat(this::class.java.classLoader.getResource("actual")?.let {
            stage1(
                input = it.readText(),
                existingColors = mapOf(
                    Color.RED to 12,
                    Color.GREEN to 13,
                    Color.BLUE to 14
                )
            )
        }).isEqualTo(2149)
    }
}
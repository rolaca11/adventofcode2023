package io.ropi.adventofcode2023.day9

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SequenceTest {
    @Test
    fun `test find next number for all 0s`() {
        val subject = Sequence(listOf(0, 0, 0, 0))

        assertThat(subject.findNextValue()).isEqualTo(0)
    }

    @Test
    fun `test find next number for 1 level of difference`() {
        val subject = Sequence(listOf(2, 2, 2, 2))

        assertThat(subject.findNextValue()).isEqualTo(2)
    }

    @Test
    fun `test find next number for 2 level of difference`() {
        val subject = Sequence(listOf(2, 4, 6, 8))

        assertThat(subject.findNextValue()).isEqualTo(10)
    }

    @Test
    fun `test find next number for 3 level of difference`() {
        val subject = Sequence(listOf(1, 3, 6, 10))

        assertThat(subject.findNextValue()).isEqualTo(15)
    }
}
package io.ropi.adventofcode2023.day5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MappingRangeTest {
    @Test
    fun `test mapping range with source in range`() {
        val subject = MappingRange(
            destinationRangeStart = 5,
            sourceRangeStart = 9,
            rangeLength = 5
        )

        assertThat(subject[11]).isEqualTo(7)
        assertThat(subject[9]).isEqualTo(5)
        assertThat(subject[13]).isEqualTo(9)
    }

    @Test
    fun `test mapping range with source out of range`() {
        val subject = MappingRange(
            destinationRangeStart = 5,
            sourceRangeStart = 9,
            rangeLength = 5
        )

        assertThat(subject[5]).isEqualTo(5)
        assertThat(subject[14]).isEqualTo(14)
    }
}
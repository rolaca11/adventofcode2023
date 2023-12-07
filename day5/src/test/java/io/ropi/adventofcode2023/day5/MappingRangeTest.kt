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

        assertThat(subject[5]).isNull()
        assertThat(subject[14]).isNull()
    }

    @Test
    fun `test map range within`() {
        val subject = MappingRange(
            destinationRangeStart = 100,
            sourceRangeStart = 10,
            rangeLength = 5
        )

        assertThat(subject.mapRange(11L..13L)).isEqualTo(101L..103L)
    }

    @Test
    fun `test map range lower start, end within`() {
        val subject = MappingRange(
            destinationRangeStart = 100,
            sourceRangeStart = 10,
            rangeLength = 5
        )

        assertThat(subject.mapRange(8L..13L)).isEqualTo(100L..103L)
    }

    @Test
    fun `test map range within start, end higher`() {
        val subject = MappingRange(
            destinationRangeStart = 100,
            sourceRangeStart = 10,
            rangeLength = 5
        )

        assertThat(subject.mapRange(11L..17L)).isEqualTo(101L..104L)
    }

    @Test
    fun `test map range lower start, end higher`() {
        val subject = MappingRange(
            destinationRangeStart = 100,
            sourceRangeStart = 10,
            rangeLength = 5
        )

        assertThat(subject.mapRange(8L..17L)).isEqualTo(100L..104L)
    }
}
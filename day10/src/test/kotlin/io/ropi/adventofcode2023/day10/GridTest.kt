package io.ropi.adventofcode2023.day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GridTest {
    @Test
    fun `test expanded grid 1x1`() {
        val subject = Grid.parseFrom(".")

        println(subject.expandedGrid)

        assertThat(subject.expandedGrid.tiles).isEqualTo(
            Grid.parseFrom("""
                ..
                ..
            """.trimIndent()).tiles
        )
    }

    @Test
    fun `test expanded grid 4x4`() {
        val subject = Grid.parseFrom("""
            ....
            .S-7
            .|.|
            .L-J
        """.trimIndent())

        assertThat(subject.expandedGrid.toString()).isEqualTo(
            Grid.parseFrom("""
                ........
                ........
                ..S---7.
                ..|...|.
                ..|...|.
                ..|...|.
                ..L---J.
                ........
            """.trimIndent()).toString()
        )
    }
}
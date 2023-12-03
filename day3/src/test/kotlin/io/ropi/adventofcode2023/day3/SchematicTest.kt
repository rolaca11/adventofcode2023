package io.ropi.adventofcode2023.day3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SchematicTest {

    @Test
    fun `test schematic construct 1`() {
        val schematic = Schematic.parseFrom("""
            1#
        """.trimIndent())

        assertThat(schematic.partNumbers).hasSize(1)
    }

    @Test
    fun `test schematic construct 2`() {
        val schematic = Schematic.parseFrom("""
            1.#
        """.trimIndent())

        assertThat(schematic.partNumbers).hasSize(0)
    }

    @Test
    fun `test schematic construct 3`() {
        val schematic = Schematic.parseFrom("""
            .#
            1.
        """.trimIndent())

        assertThat(schematic.partNumbers).hasSize(1)
    }
}
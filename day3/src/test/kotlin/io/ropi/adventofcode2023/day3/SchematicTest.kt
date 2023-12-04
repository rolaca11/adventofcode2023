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

    @Test
    fun `test schematic construct 4`() {
        val schematic = Schematic.parseFrom("""
            #.
            .1
        """.trimIndent())

        assertThat(schematic.partNumbers).hasSize(1)
    }

    @Test
    fun `test schematic construct 5`() {
        val schematic = Schematic.parseFrom("""
            1.
            .#
        """.trimIndent())

        assertThat(schematic.partNumbers).hasSize(1)
    }

    @Test
    fun `test schematic construct 6`() {
        val schematic = Schematic.parseFrom("""
            1#
        """.trimIndent())

        assertThat(schematic.partNumbers).hasSize(1)
    }

    @Test
    fun `test schematic construct 7`() {
        val schematic = Schematic.parseFrom("""
            .1
            #.
        """.trimIndent())

        assertThat(schematic.partNumbers).hasSize(1)
    }

    @Test
    fun `test schematic construct 8`() {
        val schematic = Schematic.parseFrom("""
            .1111
            #....
        """.trimIndent())

        assertThat(schematic.partNumbers).hasSize(1)
    }

    @Test
    fun `test schematic construct 9`() {
        val schematic = Schematic.parseFrom("""
            .1111.
            .....#
        """.trimIndent())

        assertThat(schematic.partNumbers).hasSize(1)
    }

    @Test
    fun `test schematic construct 10`() {
        val schematic = Schematic.parseFrom("""
            .....#
            .1111.
        """.trimIndent())

        assertThat(schematic.partNumbers).hasSize(1)
    }

    @Test
    fun `test schematic construct 11`() {
        val schematic = Schematic.parseFrom("""
            /.....
            .1111.
        """.trimIndent())

        assertThat(schematic.partNumbers).hasSize(1)
    }

    @Test
    fun `test schematic construct 12`() {
        val schematic = Schematic.parseFrom("""
            322*512
        """.trimIndent())

        assertThat(schematic.partNumbers).hasSize(2)
    }

    @Test
    fun `test schematic construct 13`() {
        val schematic = Schematic.parseFrom("""
            .&.
            391
        """.trimIndent())

        assertThat(schematic.partNumbers).hasSize(1)
    }
}
package io.ropi.adventofcode2023.day5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MappingTest {
    @Test
    fun `test range mapping with winning example`() {
        val almanac = parseStage2(this::class.java.classLoader.getResource("stage1-example").readText())

        assertThat(almanac.mapping[82L..82L].flatten().min()).isEqualTo(46)
    }

    @Test
    fun `test range mapping with winning example range`() {
        val almanac = parseStage2(this::class.java.classLoader.getResource("stage1-example").readText())

        assertThat(almanac.mapping[79L..92L].flatten().min()).isEqualTo(46)
    }
}
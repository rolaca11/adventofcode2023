package io.ropi.adventofcode2023.day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TileTest {

    @Test
    fun `test unconnected pipe in component with empty tile`() {
        val subject = Grid.parseFrom("F.F")

        assertThat(subject[0][0]?.isInSameComponentAs(subject[1][0]!!, subject)).isTrue()
        assertThat(subject[2][0]?.isInSameComponentAs(subject[1][0]!!, subject)).isTrue()
    }
}
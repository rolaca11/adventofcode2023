package io.ropi.adventofcode2023.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RaceTest {
    @Test
    fun `test number of ways to win`() {
        val subject = Race(listOf(
            Run(7.0, 9.0),
            Run(15.0, 40.0),
            Run(30.0, 200.0),
        ))

        assertThat(subject.listNumOfWaysToWin().reduce { i, j -> i*j }).isEqualTo(288)
    }
}
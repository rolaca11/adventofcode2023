package io.ropi.adventofcode2023.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RunTest {
    @Test
    fun `test button held for`() {
        val subject = Run(raceLength = 7.0, recordDistance = 9.0)

        assertThat(subject.holdLengthOfRecord).isLessThan(2.0).isGreaterThan(1.0)
    }

    @Test
    fun `test number of ways to beat record`() {
        val subject = Run(raceLength = 7.0, recordDistance = 9.0)

        assertThat(subject.numberOfWaysToBeatRecord).isEqualTo(4)
    }

    @Test
    fun `test number of ways to beat record 2`() {
        val subject = Run(raceLength = 30.0, recordDistance = 200.0)

        assertThat(subject.numberOfWaysToBeatRecord).isEqualTo(9)
    }
}
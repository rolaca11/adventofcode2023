package io.ropi.adventofcode2023.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Stage1KtTest {

    @Nested
    inner class IndividualFunctions {
        @Test
        fun `test find first digit`() {
            assertThat("adwf1hoif235".`first digit`()).isEqualTo(1)
        }

        @Test
        fun `test calibration value`() {
            assertThat("a line 1 that 5 has some letters".`calibration value`()).isEqualTo(15)
        }
    }

    @Nested
    inner class ActualInputs {

        @Test
        fun `example input`() {
            assertThat(this::class.java.classLoader.getResource("stage1-example")?.let { start(it.readText()) })
                .isEqualTo(142)
        }

        @Test
        fun `actual input`() {
            assertThat(this::class.java.classLoader.getResource("stage1")?.let { start(it.readText()) })
                .isEqualTo(55488)
        }
    }
}
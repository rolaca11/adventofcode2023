package io.ropi.adventofcode2023.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Stage2KtTest {
    @Test
    fun stage2Example() {
        assertThat(this::class.java.classLoader.getResource("stage1-example")?.let { stage2(it.readText()) })
            .isEqualTo(71503)
    }

    @Test
    fun stage2Actual() {
        assertThat(this::class.java.classLoader.getResource("actual")?.let { stage2(it.readText()) })
            .isEqualTo(46561107)
    }
}
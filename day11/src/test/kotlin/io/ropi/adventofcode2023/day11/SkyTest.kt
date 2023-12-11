package io.ropi.adventofcode2023.day11

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SkyTest {
    @Test
    fun `test expansion factor first column not empty`() {
        val subject = Sky.parseFrom("""
            ....
            #...
        """.trimIndent())

        assertThat(subject[0]!![1].position.x).isEqualTo(1)
        assertThat(subject[0]!![0].position.x).isEqualTo(0)
        assertThat(subject.expanded(2)[0]!![0].position.x).isEqualTo(0)
        assertThat(subject.expanded(2)[0]!![1].position.x).isEqualTo(2)
        assertThat(subject.expanded(10)[0]!![0].position.x).isEqualTo(0)
        assertThat(subject.expanded(10)[0]!![1].position.x).isEqualTo(10)
        assertThat(subject.expanded(100)[0]!![0].position.x).isEqualTo(0)
        assertThat(subject.expanded(100)[0]!![1].position.x).isEqualTo(100)

        assertThat(subject[0]!![3].position.x).isEqualTo(3)
        assertThat(subject.expanded(2)[0]!![3].position.x).isEqualTo(6)
        assertThat(subject.expanded(10)[0]!![3].position.x).isEqualTo(30)
        assertThat(subject.expanded(100)[0]!![3].position.x).isEqualTo(300)
    }

    @Test
    fun `test expansion factor first and third column not empty`() {
        val subject = Sky.parseFrom("""
            ....
            #.#.
        """.trimIndent())

        assertThat(subject[0]!![1].position.x).isEqualTo(1)
        assertThat(subject[0]!![0].position.x).isEqualTo(0)
        assertThat(subject.expanded(2)[0]!![0].position.x).isEqualTo(0)
        assertThat(subject.expanded(2)[0]!![1].position.x).isEqualTo(2)
        assertThat(subject.expanded(10)[0]!![0].position.x).isEqualTo(0)
        assertThat(subject.expanded(10)[0]!![1].position.x).isEqualTo(10)
        assertThat(subject.expanded(100)[0]!![0].position.x).isEqualTo(0)
        assertThat(subject.expanded(100)[0]!![1].position.x).isEqualTo(100)

        assertThat(subject[0]!![3].position.x).isEqualTo(3)
        assertThat(subject.expanded(2)[0]!![3].position.x).isEqualTo(5)
        assertThat(subject.expanded(10)[0]!![3].position.x).isEqualTo(21)
        assertThat(subject.expanded(100)[0]!![3].position.x).isEqualTo(201)
    }
}
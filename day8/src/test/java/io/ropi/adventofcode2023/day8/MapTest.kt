package io.ropi.adventofcode2023.day8

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class MapTest {
    @Test
    fun `test next node`() {
        val endingNode = Map.Node(null, null)
        val subject = Map(
            startingNode = Map.Node(left = endingNode, right = null),
            endingNode = endingNode,
            directions = listOf(Map.Direction.LEFT)
        )

        assertThat(subject.stepsToGetEndingNode()).containsExactly(Map.Direction.LEFT)
    }
}
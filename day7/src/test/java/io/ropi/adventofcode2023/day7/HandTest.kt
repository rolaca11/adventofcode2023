package io.ropi.adventofcode2023.day7

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `test expanding joker`() {
        val subject = Hand.parseFrom("2345J")

        assertThat(subject.jokerExpandedHands).containsExactlyInAnyOrder(
            Hand.parseFrom("23452"),
            Hand.parseFrom("23453"),
            Hand.parseFrom("23454"),
            Hand.parseFrom("23455"),
            Hand.parseFrom("23456"),
            Hand.parseFrom("23457"),
            Hand.parseFrom("23458"),
            Hand.parseFrom("23459"),
            Hand.parseFrom("2345T"),
            Hand.parseFrom("2345Q"),
            Hand.parseFrom("2345J"),
            Hand.parseFrom("2345K"),
            Hand.parseFrom("2345A"),
        )
    }
}
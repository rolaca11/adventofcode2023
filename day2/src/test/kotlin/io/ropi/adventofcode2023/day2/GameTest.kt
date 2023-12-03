package io.ropi.adventofcode2023.day2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameTest {

    @Test
    fun `test parse from string`() {
        assertThat(Game.parseFrom("Game 1: 5 blue, 3 red; 10 green, 15 blue")).isEqualTo(Game(
            id = 1,
            samples = listOf(
                Sample(colors = mapOf(Color.BLUE to 5, Color.RED to 3)),
                Sample(colors = mapOf(Color.BLUE to 15, Color.GREEN to 10)),
            )
        ))
    }

    @Test
    fun `test game validity with valid test`() {
        val game = Game.parseFrom("Game 1: 5 blue, 3 red; 10 green, 15 blue")
        assertThat(game.isValid(mapOf(Color.BLUE to 20, Color.RED to 4, Color.GREEN to 10))).isTrue()
    }

    @Test
    fun `test game validity with invalid test`() {
        val game = Game.parseFrom("Game 1: 5 blue, 3 red; 10 green, 15 blue")
        assertThat(game.isValid(mapOf(Color.BLUE to 2, Color.RED to 4, Color.GREEN to 12))).isFalse()
    }
}
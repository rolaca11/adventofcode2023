package io.ropi.adventofcode2023.day7

data class Game(val plays: List<Play>) {
    val rankings
        get() = plays.sortedBy { it.hand.strength }.mapIndexed { index, play -> play to index + 1 }.toMap()

    companion object {
        fun parseFrom(input: String) = Game(input.lines().filter { it.isNotBlank() }.map { Play.parseFrom(it) })
    }
}
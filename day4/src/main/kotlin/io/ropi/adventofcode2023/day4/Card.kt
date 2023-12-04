package io.ropi.adventofcode2023.day4

import kotlin.math.pow

data class Card(
    val id: Int,
    val winningNumbers: List<Int>,
    val actualNumbers: List<Int>
) {
    val value: Int
        get() = winningNumbers.filter { actualNumbers.contains(it) }.size
            .let { if (it > 0) 2.0.pow(it - 1).toInt() else null } ?: 0

    companion object {
        private val CARD_REGEX = Regex("Card\\s+(\\d+):\\s+([0-9\\s]+) \\|\\s+([0-9\\s]+)")

        fun parseFrom(input: String): Card {
            val match = CARD_REGEX.matchEntire(input) ?: throw Error(input)

            return Card(
                id = match.groups[1]?.value?.toInt() ?: throw Error(),
                winningNumbers = match.groups[2]?.value?.split(regex = Regex("\\s+"))?.map { it.toInt() } ?: emptyList(),
                actualNumbers = match.groups[3]?.value?.split(regex = Regex("\\s+"))?.map { it.toInt() } ?: emptyList()
            )
        }
    }
}
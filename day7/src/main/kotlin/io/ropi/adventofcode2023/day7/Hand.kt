package io.ropi.adventofcode2023.day7

import kotlin.math.pow

class Hand(
    val cards: List<Card>
) {

    private val highestCombo = Combos.entries.filter { it.isApplicable(this) }.maxByOrNull { it.value }

    val strength
        get() = (highestCombo?.value ?: 0) * 16.0.pow(6) +
                cards.mapIndexed { index, card -> card.value * 16.0.pow(5 - index) }.sum()

    override fun toString() = "${cards.map { it.label }.joinToString()}($strength)"

    companion object {
        fun parseFrom(input: String) =
            Hand(input.map { label -> Card.entries.find { card -> card.label == label }!! })
    }

}
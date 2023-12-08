package io.ropi.adventofcode2023.day7

import kotlin.math.pow

class Hand(
    val cards: List<Card>
) {

    private val highestCombo = Combos.entries.filter { it.isApplicable(this) }.maxByOrNull { it.value }

    val jokerExpandedHands by lazy { expandJoker(emptyList(), cards) }

    private val highestComboWithJoker
        get() = Combos.entries.filter { it.isApplicableWithJoker(this) }.maxByOrNull { it.value }

    val strength
        get() = (highestCombo?.value ?: 0) * 16.0.pow(6) +
                cards.mapIndexed { index, card -> card.value * 16.0.pow(5 - index) }.sum()

    val strengthWithJoker
        get() = (highestComboWithJoker?.value ?: 0) * 16.0.pow(6) +
                cards.mapIndexed { index, card -> card.valueStage2 * 16.0.pow(5 - index) }.sum()


    private fun expandJoker(acc: List<Card>, remainingCards: List<Card>): List<Hand> {
        if(remainingCards.isEmpty()) {
            return listOf(Hand(acc));
        }

        val nextCard = remainingCards[0]

        return if(nextCard.label == 'J') {
            Card.entries.flatMap { expandJoker(acc + it, remainingCards.drop(1)) }
        } else {
            expandJoker(acc + nextCard, remainingCards.drop(1))
        }
    }


    override fun toString() = "${cards.map { it.label }.joinToString()}($strength)"
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Hand

        return cards == other.cards
    }

    override fun hashCode(): Int {
        return cards.hashCode()
    }


    companion object {
        fun parseFrom(input: String) =
            Hand(input.map { label -> Card.entries.find { card -> card.label == label }!! })
    }

}